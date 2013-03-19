package util;

public interface Row {
	Case getCase(int col);
	boolean isValid();
	boolean isFull();
	void addPossibleCandidates(int v);
	void removePossibleCandidates(int v);
	void setCase(int col, int v);
}
