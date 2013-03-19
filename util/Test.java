package util;

import java.awt.Dimension;

import gui.GraphicGrid;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent.KeyBinding;

public class Test {

	private JFrame frame;
	private StdGrid model;
	private JButton test;
	
	public Test() {
		model = new StdGrid();
		fillSudoku();
		display();
		model.setCase(0, 0, 5);
		System.out.print("\n\n");
		display();
		boolean validLine = model.getRow(0).isValid();
		System.out.println("\n");
		System.out.println(validLine ? "Ligne 0 Valide" : "Ligne 0 invalide");
		System.out.println("\n");
		model.clearGrid();
		display();
	}
	
	
    public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
	private void fillSudoku() {
		int higher = 10;
		int lower = 1;
		int random;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				random = (int) (Math.random() * (higher - lower)) + lower;
				model.setCase(i, j, random);
			}
		}
	}
	
	private void placeComponents() {
		frame = new JFrame("Sudoku");
		test = new JButton("test");
		GraphicGrid grid = new GraphicGrid(model);
		
		
	}

	public static void main(String[] args) {
		new Test();
		
	}

}
