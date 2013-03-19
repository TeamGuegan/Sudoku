package gui;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import util.Block;
import util.Case;
import util.Column;
import util.Row;
import util.StdGrid;

public class GraphicGrid extends JPanel {
	private static final Color SELECTED_CASE_COLOR = new Color(187, 210, 225);
	private static final int CASE_SIZE = 50;
	private static final int KEY_CODE_FOR_ZERO = 96;
	private static final int KEY_CODE_FOR_NINE = 105;
	
	private StdGrid sudoku;
	private int selectedCaseRow;
	private int selectedCaseCol;
	private Case[] errorCases;
	
	public GraphicGrid(StdGrid g) {
		super(new GridLayout(9, 9));
		//pour que le keyListener soit pris en compte
		setFocusable(true);
		requestFocus();
		sudoku = g;
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				GraphicCase gc = new GraphicCase(sudoku, i, j);
				add(gc);
			}
		}
		selectedCaseRow = -1;
		selectedCaseCol = -1;
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedCaseRow != -1 && selectedCaseCol != -1) {
					sudoku.getCase(selectedCaseRow, selectedCaseCol)
						.setColor(Color.WHITE);
				}
				if (errorCases != null) {
					for (int i = 0; i < errorCases.length; i++) {
						errorCases[i].setColor(Color.WHITE);
					}
				}
				int row, col;
				row = e.getY() / CASE_SIZE;
				col = e.getX() / CASE_SIZE;
				selectedCaseRow = row;
				selectedCaseCol = col;
				sudoku.getCase(selectedCaseRow, selectedCaseCol)
					.setColor(SELECTED_CASE_COLOR);
				requestFocus();
				repaint();
			}
		});
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				if (selectedCaseRow != -1 && selectedCaseCol != -1 
						&& e.getKeyCode() > KEY_CODE_FOR_ZERO 
						&& e.getKeyCode() <= KEY_CODE_FOR_NINE) {
					errorCases = null;
					int value = Integer.parseInt((e.getKeyChar()) + "");
					try {
						sudoku.setCase(selectedCaseRow, selectedCaseCol, value);
					} catch (IllegalStateException ise) {
						errorCases = getErrorCases(value);
						if (errorCases != null) {
							for (int i = 0; i < errorCases.length; i++) {
								errorCases[i].setColor(Color.RED);
								repaint();
							}	
						}
					}
					repaint();
				}
			}
		});
	}
	
	public Case getSelectedCase() {
		return sudoku.getCase(selectedCaseRow, selectedCaseCol);
	}
	
	private Case[] getErrorCases(int value) {
		List<Case> errors = new ArrayList<Case>();
		Row row = sudoku.getRow(selectedCaseRow);
		Column col = sudoku.getColumn(selectedCaseCol);
		for (int i = 0; i < 9; i++) {
			if (row.getCase(i).getValue() == value) {
				errors.add(row.getCase(i));
			}
			if (col.getCase(i).getValue() == value) {
				errors.add(col.getCase(i));
			}
		}
		Block block = sudoku.getBlock(selectedCaseCol, selectedCaseRow);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (block.getCase(i, j).getValue() == value) {
					errors.add(block.getCase(i, j));
				}
			}
		}
		if (errors.size() == 0) {
			return null;
		} else {
			Case[] errorCase = new Case[errors.size()];
			for (int i = 0; i < errors.size(); i++) {
				errorCase[i] = errors.get(i);
			}
			return errorCase;
		}
	}	
}
