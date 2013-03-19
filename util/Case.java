package util;

import java.util.List;

public interface Case {
	int getValue();
	void setValue(int v);
	void clear();
	List<Integer> getCandidates();
}

