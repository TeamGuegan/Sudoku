package util;

import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class StdGrid implements Grid {
	
	public static final int CASE_NB = 81,
				DIM = 9;
	
	//ATTRIBUTS
	private Case[][] grid;
	//Nbr de cases libres de la grille
	private int free;
	//map de block
	private final Map<Integer, Block> dataBlock;
	//map de ligne
	private final Map<Integer, Row> dataRow;
	//map de colonne
	private final Map<Integer, Column> dataColumn;
	
	private EventListenerList listenerList;
	private ChangeEvent changeEvent;
	
	//CONSTRUCTEUR
	public StdGrid() {
		grid = new StdCase[DIM][DIM];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid [i][j] = new StdCase();
			}
		}
		free = CASE_NB;
		dataBlock = new HashMap<Integer, Block>();
		initDataBlock();
		dataColumn = new HashMap<Integer, Column>();
		initDataColumn();
		dataRow = new HashMap<Integer, Row>();
		initDataRow();
		listenerList = new EventListenerList();
	}
	
	//REQUETES	
	public Case getCase(int row, int col) {
		if (row < 0 || row > 8 || col < 0 || col > 8) {
			throw new IllegalArgumentException();
		}
		return grid[row][col];
	}
	
	public Block getBlock(int numC, int numR) {
		if (numC < 0 || numC > 8) {
			throw new IllegalArgumentException("Mauvais numéro de colonnes");
		}
		if (numR < 0 || numR > 8) {
			throw new IllegalArgumentException("Mauvais numéro de lignes");
		}
		int numBlock;
		if (numR < 3) {
			if (numC < 3) {
				numBlock = 1;
			} else if (numR < 6) {
				numBlock = 2;
			} else {
				numBlock = 3;
			}
		} else if (numR < 6) {
			if (numC < 3) {
				numBlock = 4;
			} else if (numR < 6) {
				numBlock = 5;
			} else {
				numBlock = 6;
			}
		} else {
			if (numC < 3) {
				numBlock = 7;
			} else if (numR < 6) {
				numBlock = 8;
			} else {
				numBlock = 9;
			}
		}
		return getBlock(numBlock);
	}
	
	public Block getBlock(int blockNb) {
		if (blockNb < 1 || blockNb > 9) {
			throw new IllegalArgumentException("Le block n'existe pas");
		}
		return dataBlock.get(blockNb);
	}
	
	public Row getRow(int r) {
		if (r < 0 || r > 8) {
			throw new IllegalArgumentException("Mauvais numero de ligne");
		}
		return dataRow.get(r);
	}
	
	public Column getColumn(int c) {
		if (c < 0 || c > 8) {
			throw new IllegalArgumentException("Mauvais numero de colonne");
		}
		return dataColumn.get(c);
	}
	
	public Block[] getColumnBlock(int c) {
		if (c < 0 || c > 2) {
			throw new IllegalArgumentException();
		}
		Block[] tab = new StdBlock[DIM / 3];
		tab[0] = getBlock(c + 1);
		tab[1] = getBlock(c + 4);
		tab[2] = getBlock(c + 7);
		return tab;
	}
	
	public Block[] getRowBlock(int r) {
		if (r < 0 || r > 2) {
			throw new IllegalArgumentException();
		}
		Block[] tab = new StdBlock[DIM / 3];
		switch (r) {
		case 0 :
			tab[0] = getBlock(1);
			tab[1] = getBlock(2);
			tab[2] = getBlock(3);
			break;
		case 1 : 
			tab[0] = getBlock(4);
			tab[1] = getBlock(5);
			tab[2] = getBlock(6);
			break;
		case 2 : 
			tab[0] = getBlock(7);
			tab[1] = getBlock(8);
			tab[2] = getBlock(9);
			break;
		default :
			throw new IllegalArgumentException();
		}
		return tab;
	}
	
	public int getNbOfFreeCase() {
		return free;
	}
	
	// COMMANDES
	public void setCase(int r, int c, int v) {
		if (c < 0 || c > 8) {
			throw new IllegalArgumentException();
		}
		if (r < 0 || r > 8) {
			throw new IllegalArgumentException();
		}
		if (v < 1 || v > 9) {
			throw new IllegalArgumentException();
		}
		grid[r][c].setValue(v);
		free--;
		Row row = getRow(r);
		row.removePossibleCandidates(v);
		Column col = getColumn(c);
		col.removePossibleCandidates(v);
		Block block = getBlock(c, r);
		block.removePossibleCandidates(v);
		fireStateChanged();
	}
	
	public void clearCase(int c, int r) {
		if (c < 0 || c > 8) {
			throw new IllegalArgumentException();
		}
		if (r < 0 || r > 8) {
			throw new IllegalArgumentException();
		}
		int v = grid[r][c].getValue();
		grid[r][c].clear();
		free++;
		if (v != 0) {
			Row row = getRow(r);
			row.addPossibleCandidates(v);
			Column col = getColumn(c);
			col.addPossibleCandidates(v);
			Block block = getBlock(c, r);
			block.addPossibleCandidates(v);
		}
		fireStateChanged();
	}
	
	public void clearGrid() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				clearCase(c, r);
			}
		}
		fireStateChanged();
	}
	
	public void addChangeListener(ChangeListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
    	listenerList.add(ChangeListener.class, listener);
	}
	
	public void removeChangeListener(ChangeListener listener) {
		if (listener == null) {
    		throw new IllegalArgumentException();
		}
    	listenerList.remove(ChangeListener.class, listener);
	}

	
	// OUTILS
	
//	Ces deux fonctions sont elles utiles???
//	
//	private boolean isValidBlockRow(int r) {
//		return false;
//	}
//	
//	private boolean isValidBlockColumn(int c) {
//		return false;
//	}
	
	//initialise tous les blocks avec les cases de la grille
	private void initDataBlock() {
		int r = 0, c = 0, tmp, tmp2;
		Case[][] tab;
		for (int i = 1; i <= DIM; i++) {
			if (((i % 3) - 1) == 0 && i != 1) {
				r = i - 1;
				c = 0;
			}
			tab = new StdCase[DIM / 3][DIM / 3];
			tmp = r;
			tmp2 = c;
			while (c <= tmp2 + 2) {
				while (r <= tmp + 2) {
					tab[r % 3][c % 3] = grid[r][c];
					r++;
				}
				r = tmp;
				c++;
			}
			dataBlock.put(i, new StdBlock(tab));
		}
	}
	
	private void initDataColumn() {
		Case[] tab;
		for (int c = 0; c < 9; c++) {
			tab = new StdCase[DIM];
			for (int r = 0; r < 9; r++) {
				tab[r] = grid[r][c];
			}
			dataColumn.put(c, new StdColumn(tab));
		}
	}
	
	private void initDataRow() {
		Case[] tab;
		for (int r = 0; r < 9; r++) {
			tab = new StdCase[DIM];
			for (int c = 0; c < 9; c++) {
				tab[c] = grid[r][c];
			}
			dataRow.put(r, new StdRow(tab));
		}
	}
	
	protected void fireStateChanged() {
    	for (ChangeListener cl
    			: listenerList.getListeners(ChangeListener.class)) {
    		if (changeEvent == null) {
    			changeEvent = new ChangeEvent(this);
    		}
    		cl.stateChanged(changeEvent);
    	}
    }
}
