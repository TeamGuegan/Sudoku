package util;

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
	private int value;
	private List<Integer> candidates;
	private List<Integer> possibleCandidates;
	
	// CONSTRUCTEURS
	public StdCase() {
		value = 0;
		candidates = new ArrayList<Integer>();
		createPossibleCandidatesList();
	}
	
	// REQUETE 
	public int getValue() {
		return value;
	}
	
	public List<Integer> getPossibleCandidates() {
		return possibleCandidates;
	}
	
	public List<Integer> getCandidates() {
		return candidates;
	}
	
	// COMMANDES
	public void setValue(int v) {
		if (!isValidValue(v)) {
			throw new IllegalArgumentException();
		}
		if (!possibleCandidates.contains(v)) {
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
		value = 0;
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
