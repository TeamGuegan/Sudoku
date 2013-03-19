package gui;


import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import util.StdGrid;

public class GraphicGrid extends JPanel {
	private StdGrid sudoku;
	private BasicStroke bs;
	private static final int MARGIN = 25;
	private static final int GRID_SIZE = 450;
	private static final int WIDTH = 500;
	private static final int HEIGTH = 500;
	private static final int CASE_SIZE = 50;
	
	public GraphicGrid(StdGrid g) {
		super(new GridLayout(9, 9));
		
		sudoku = g;
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				System.out.println("graphicGrid");
				GraphicCase gc = new GraphicCase(sudoku.getCase(i, j), i, j);
				add(gc);
			}
		}
		//repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		bs = new BasicStroke(2.0f);
		
		g2d.setStroke(bs);
		
		
		g.drawLine(25, 25, 475, 25);
		g.drawLine(25, 175, 475, 175);
		g.drawLine(25, 325, 475, 325);
		g.drawLine(25, 475, 475, 475);
		g.drawLine(25, 25, 25, 475);
		g.drawLine(175, 25, 175, 475);
		g.drawLine(325, 25, 325, 475);
		g.drawLine(475, 25, 475, 475);
	}
	
}
