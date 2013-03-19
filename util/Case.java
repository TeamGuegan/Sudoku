package util;

import java.awt.Color;
import java.util.List;

public interface Case {
	Color getColor();
	int getValue();
	List<Integer> getPossibleCandidates();
	List<Integer> getCandidates();
	boolean isLocked();
	void setColor(Color c);
	void addPossibleCandidate(int v);
	void removePossibleCandidate(int v);
	void setValue(int v);
	void removeCandidates(int v);
	void clear();
	void setLocked(boolean locked);
}

