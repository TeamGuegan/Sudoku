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

import util.Case;

public class GraphicCase extends JComponent {
	private Case model;
	private Dimension coord;
	private Color color;
	private int row;
	private int col;
	private static final int CASE_SIZE = 50;
	private static final int FONT_SIZE = 10;
	private static final int MARGIN_CASE = 5;
	private static final int MARGIN = 25;
	
	public GraphicCase(Case c, int row, int col) {
		this(c, Color.WHITE, row, col);
	}
	
	public GraphicCase(Case c, Color color, int row, int col) {
		if (c == null) {
			throw new IllegalArgumentException("Pas de case");
		}
		if (row < 0 || row > 8) {
			throw new IllegalArgumentException("Mauvais numéro ligne");
		}
		if (col < 0 || col > 8) {
			throw new IllegalArgumentException("Mauvais numéro colonne");
		}
		model = c;
		this.row = row;
		this.col = col;
		this.color = color;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setPreferredSize(new Dimension(50, 50));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, 
				CASE_SIZE, CASE_SIZE);
		/**if (model.getValue() == 0) {
			paintCandidates(g);
		} else {
			paintValue(g);
		}*/
	}
	
	private void paintValue(Graphics g) {
		if (g == null) {
			throw new IllegalArgumentException();
		}
		FontMetrics fm = g.getFontMetrics();
		String s = String.valueOf(model.getValue());
		int sWidth = fm.stringWidth(s);
		g.drawString(s, (int) coord.getWidth() - 1 / 2 
				* sWidth + 1 / 2 * CASE_SIZE, 
				(int) coord.getHeight() - 1 / 2 * sWidth + 1 / 2 * CASE_SIZE);
	}
	
	private void paintCandidates(Graphics g) {
		Font f = g.getFont();
		g.setColor(Color.BLUE);
		String fontName = g.getFont().getFontName();
		int fontStyle = g.getFont().getStyle();
		Graphics2D g2d = (Graphics2D) g;
		Font f2 = new Font(fontName, fontStyle, FONT_SIZE);
		g2d.setFont(f2);
		List<Integer> l = model.getCandidates(); 
		if (l.size() != 0) {
			int coordX = (int) coord.getWidth();
			int coordY = (int) coord.getHeight();
			coordY = coordY + MARGIN_CASE;
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
