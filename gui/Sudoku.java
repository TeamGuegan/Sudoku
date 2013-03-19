package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.StdCase;
import util.StdGrid;

public class Sudoku {
	private JFrame frame;
	private StdGrid model;
	private JButton test;
	private GraphicGrid grid;
	
	public Sudoku() {
		model = new StdGrid();
		createView();
		//grid = new GraphicGrid(model); 
		placeComponents();
	}
	
    private void createView() {
    	frame = new JFrame();
    	//frame.setPreferredSize(new Dimension(
    	//		600, 600));
    	test = new JButton("test");
	}

	public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Sudoku().display();
            }
        });
    }
    
	@SuppressWarnings("unused")
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
		//frame.add(grid, BorderLayout.CENTER);
		JPanel p = new JPanel(new GridLayout(9, 0)); {
			for (int i = 0; i < 9; i++) {
				JPanel q = new JPanel(new GridLayout(0, 9)); {
					for (int j = 0; j < 9; j++) {
						p.add(new GraphicCase(model.getCase(i, j), i, j));
					}
				}
				p.add(q);
			}
		}
		frame.add(p, BorderLayout.NORTH);
		frame.add(test, BorderLayout.SOUTH);
	}
}
