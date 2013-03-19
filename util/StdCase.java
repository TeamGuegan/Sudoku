package util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @inv
 * 	candidates != null
 *  possibleCandidates != null
 *	value >= 0 && value <= 9
 */

public class StdCase implements Case {
	
	// ATTRIBUTS
	private static final Color LOCKED_CASE_COLOR = new Color(206, 206, 206);
	
	private int value;
	private List<Integer> candidates;
	private List<Integer> possibleCandidates;
	private boolean isLocked;
	private Color color;
	
	// CONSTRUCTEURS
	public StdCase() {
		this(false);
	}
	
	public StdCase(boolean locked) {
		value = 0;
		isLocked = locked;
		candidates = new ArrayList<Integer>();
		createPossibleCandidatesList();
		if (isLocked) {
			color = LOCKED_CASE_COLOR;
		} else {
			color = Color.WHITE;
		}
	}
	
	// REQUETE 
	public Color getColor() {
		return color;
	}
	
	public int getValue() {
		return value;
	}
	
	public List<Integer> getPossibleCandidates() {
		return possibleCandidates;
	}
	
	public List<Integer> getCandidates() {
		return candidates;
	}
	
	public boolean isLocked() {
		return isLocked;
	}
	
	// COMMANDES
	public void setColor(Color c) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		color = c;
	}
	
	public void addPossibleCandidate(int v) {
		if (!isValidValue(v)) {
			throw new IllegalArgumentException();
		}
		if (!possibleCandidates.contains(v)) {
			possibleCandidates.add(v);
		}
	}
	
	public void removePossibleCandidate(int v) {
		if (!isValidValue(v)) {
			throw new IllegalArgumentException();
		}
		if (possibleCandidates.contains(v)) {
			possibleCandidates.remove(new Integer(v));
		}
	}
	
	public void setValue(int v) {
		if (!isValidValue(v)) {
			throw new IllegalArgumentException();
		}
		if (!possibleCandidates.contains(v) || isLocked) {
			throw new IllegalStateException();
		}
		value = v;
	}

	public void removeCandidates(int v) {
		if (!isValidValue(v)) {
			throw new IllegalArgumentException();
		}
		possibleCandidates.remove(new Integer(v));
	}
	
	public void clear() {
		if (isLocked) {
			throw new IllegalStateException("La case est verouillée");
		}
		value = 0;
	}
	
	public void setLocked(boolean locked) {
		isLocked = locked;
	}
	
	// OUTILS
	//initialisation des candidats
	private void createPossibleCandidatesList() {
		possibleCandidates = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			possibleCandidates.add(i);
		}
	}
	
	//Indique si value est valide
	private boolean isValidValue(int value) {
		return (value >= 1 && value <= 9);
	}
	
}
