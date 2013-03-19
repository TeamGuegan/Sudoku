package util;

public interface Grid {
	Case getCase(int row, int col);
	Block getBlock(int numC, int numR);
	Block getBlock(int blockNb);
	Row getRow(int r);
	Column getColumn(int c);
	Block[] getColumnBlock(int c);
	Block[] getRowBlock(int r);
	int getNbOfFreeCase();
	void setCase(int c, int r, int v);
	void clearCase(int c, int r);
	void clearGrid();
}
