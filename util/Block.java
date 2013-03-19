package util;

public interface Block {
	Case getCase(int r, int c);
	boolean isValid();
	boolean isFull();
	void addPossibleCandidates(int v);
	void removePossibleCandidates(int v);
	void setCase(int r, int c, int v);
}
