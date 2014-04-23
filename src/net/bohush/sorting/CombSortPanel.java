package net.bohush.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class CombSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int blueColumn = -1;
	private int greenColumn = -1;
	
	public CombSortPanel(String name, int[] list, int sleepTime) {
		super(name, list, sleepTime);
	}

	@Override
	public void run() {
		try {
			
		    int gap = list.length;
		    boolean swapped = true;
		    boolean sorted = true;
		    while (gap > 1 || swapped) {
		        if (gap > 1) {
		            gap = (int) (gap / 1.3);
		        }
		        swapped = false;
		        sorted = true;
		        System.out.println(gap);
		        for (int i = 0; i + gap < list.length; i++) {
		        	redColumn = i;
		        	blueColumn = i + gap;
					Thread.sleep(4 * sleepTime);
		            if (list[i] > list[i + gap]) {
		                int t = list[i];
		                list[i] = list[i + gap];
		                list[i + gap] = t;
		    			repaint();
		    			Thread.sleep(4 * sleepTime);
		                swapped = true;		                
		            }
		        	if((sorted) && (i > 0)) {
		        		if (list[i] > list[i - 1]) {
		        			greenColumn = i;
		        		} else {
			        		greenColumn = -1;
			        		sorted = false;	        			
		        		}
		        	}
		        	repaint();
		        }
		    }
			redColumn = -1;
			blueColumn = -1;	
			greenColumn = size - 1;
		} catch (InterruptedException e) {
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		for (int i = (greenColumn == -1 ? 0 : greenColumn); i < list.length; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		for (int i = 0; i <= greenColumn; i++) {
			g.setColor(Color.GREEN);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		if(redColumn != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
		}
		if(blueColumn != -1) {
			g.setColor(Color.BLUE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
		}
	}

}