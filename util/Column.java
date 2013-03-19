package util;

public interface Column {
	Case getCase(int row);
	boolean isValid();
	boolean isFull();
	void setCase(int row, int v);
	void addPossibleCandidates(int v);
	void removePossibleCandidates(int v);
}
