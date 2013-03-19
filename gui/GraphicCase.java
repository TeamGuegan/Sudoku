package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import util.Grid;

public class GraphicCase extends JComponent {
//	private Case model;
	private Grid model;
//	private Dimension coord;
	private int row;
	private int col;
	private static final int CASE_SIZE = 50;
	private static final int FONT_SIZE = 10;
	private static final int MARGIN_CASE = 5;
//	private static final int MARGIN = 25;
	private static final int VALUE_FONT_SIZE = 36;
	
	public GraphicCase(Grid model, int row, int col) {
		if (model == null) {
			throw new IllegalArgumentException("Pas de case");
		}
		if (row < 0 || row > 8) {
			throw new IllegalArgumentException("Mauvais numéro ligne");
		}
		if (col < 0 || col > 8) {
			throw new IllegalArgumentException("Mauvais numéro colonne");
		}
		this.model = model;
		this.row = row;
		this.col = col;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setPreferredSize(new Dimension(CASE_SIZE, CASE_SIZE));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(model.getCase(row, col).getColor());
		g.fillRect(0, 0, 
				CASE_SIZE, CASE_SIZE);
		if (model.getCase(row, col).getValue() == 0) {
			paintCandidates(g);
		} else {
			paintValue(g);
		}
	}
	
	private void paintValue(Graphics g) {
		if (g == null) {
			throw new IllegalArgumentException();
		}
		Font f = new Font("Serif", Font.PLAIN, VALUE_FONT_SIZE);
		FontMetrics fm = g.getFontMetrics();
		String s = String.valueOf(model.getCase(row, col).getValue());
		int sWidth = fm.stringWidth(s);
		setFont(f);
		g.setColor(Color.BLACK);
		g.drawString(s, CASE_SIZE / 2 - sWidth / 2, 
				CASE_SIZE / 2 + sWidth / 2);
	}
	
	private void paintCandidates(Graphics g) {
		Font f = g.getFont();
		g.setColor(Color.BLUE);
		String fontName = g.getFont().getFontName();
		int fontStyle = g.getFont().getStyle();
		Graphics2D g2d = (Graphics2D) g;
		Font f2 = new Font(fontName, fontStyle, FONT_SIZE);
		g2d.setFont(f2);
		List<Integer> l = model.getCase(row, col).getPossibleCandidates(); 
		if (l.size() != 0) {
			int coordX = 0;
			int coordY = 0;
			coordY = coordY + MARGIN_CASE * 2;
			for (int i = 1; i <= 9; i++) {
				if ((i % 3 == 1) && (i != 1)) {
					coordX = 0;
					coordY = coordY + FONT_SIZE + MARGIN_CASE;
				}
				coordX = coordX + MARGIN_CASE;
				if (l.contains(i)) {
					g.drawString(String.valueOf(i), coordX, coordY);
				}
				coordX = coordX + FONT_SIZE;
			}
		}
		g.setColor(Color.BLACK);
		g.setFont(f);
	}
	
}
