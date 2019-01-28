import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends JApplet {

    private static final long serialVersionUID = 1L;
    private SortPanel[] sortPanels;
    {
        sortPanels = new SortPanel[3];
    }

    private Main() {
        setLayout(new GridLayout(1, 1, 0, 0));
        SortPanelsHolder sortPanelHolder = new SortPanelsHolder();
        sortPanelHolder.setLayout(new  GridLayout(3, 1, 0, 0));
        sortPanelHolder.setBackground(Color.BLACK);
        sortPanelHolder.setForeground(Color.BLACK);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 3;
        int height = screenSize.height / 3;
        int sleepTime = 2;
        sortPanels[0] = new SelectionSortPanel(" Selection Sort ", sleepTime, width, height);
        sortPanels[1] = new MergeSortPanel(" Merge Sort ", sleepTime, width, height);
        sortPanels[2] = new BubbleSortPanel(" Bubble Sort ", sleepTime, width, height);

        for (SortPanel sortPanel : sortPanels) {
            sortPanel.setVisible(false);
            sortPanelHolder.add(sortPanel);
        }
        add(sortPanelHolder);
    }

    class SortPanelsHolder extends JPanel {
        private static final long serialVersionUID = 1L;
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            Font animationNameFont = new Font(Font.MONOSPACED, Font.BOLD, 150);
            FontMetrics animationNameFontFontMetrix = getFontMetrics(animationNameFont);
            g.setFont(animationNameFont);
            String animationName = "";
            int x = (getWidth() - animationNameFontFontMetrix.stringWidth(animationName)) / 2;
            int y = (getHeight() - animationNameFontFontMetrix.getLeading()) / 2;
            g.drawString(animationName, x, y);
        }
    }

    private void beginAnimation(int[] list) {
        try {

            for (SortPanel sortPanel : sortPanels) {
                sortPanel.setList(list);
                sortPanel.setVisible(true);
            }
            Thread.sleep(1000);
            ExecutorService executor = Executors.newFixedThreadPool(sortPanels.length);
            for (SortPanel sortPanel : sortPanels) {
                executor.execute(sortPanel);
            }
            executor.shutdown();
            while(!executor.isTerminated()) {
                Thread.sleep(100);
            }
            Thread.sleep(1000);
            for (SortPanel sortPanel : sortPanels) {
                sortPanel.setVisible(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Algorithm Animations");
        Main main = new Main();
        frame.add(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        int size = 100;
        int[] list = new int[size];

        for (int i = 0; i < list.length; i++) {
            list[i] = i + 1;
        }
        for (int i = 0; i < list.length; i++) {
            int index = (int) (Math.random() * list.length);
            int temp = list[i];
            list[i] = list[index];
            list[index] = temp;
        }
        main.beginAnimation(list);


    }
}