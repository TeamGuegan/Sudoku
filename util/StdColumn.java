package util;

public class StdColumn implements Column {
	
	// ATTRIBUTS
	private Case[] column;
	
	// CONSTRUCTEUR
	StdColumn(Case[] tab) {
		column = tab;
	}
	
	// REQUETES
	public Case getCase(int row) {
		if (row < 0 || row > 8) {
			throw new IllegalArgumentException("Mauvais numéro de colonne");
		}
		return column[row];
	}
	
	public boolean isValid() {
		for (int i = 0; i < 8; i++) {
			if (!isValidValue(i)) {
				return false;
			}
			for (int j = i + 1; j < 9; j++) {
				if (column[i].getValue() == column[j].getValue()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isFull() {
		for (int i = 0; i <= 8; i++) {
			if (column[i].getValue() == 0) {
				return false;
			}
		}
		return true;
	}
	
	// COMMANDES
	public void addPossibleCandidates(int v) {
		if (!isValidValue(v) || v == 0) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < 9; i++) {
			column[i].addPossibleCandidate(v);
		}
	}
	
	public void removePossibleCandidates(int v) {
		if (!isValidValue(v) || v == 0) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < 9; i++) {
			column[i].removePossibleCandidate(v);
		}
	}
	public void setCase(int row, int v) {
		if (row < 0 || row > 8) {
			throw new IllegalArgumentException("Mauvais numéro de colonne");
		}
		if (!isValidValue(v)) {
			throw new IllegalArgumentException("Mauvaise valeur");
		}
		column[row].setValue(v);
	}
	
	// OUTILS
	// 0 inclus dans les valeurs possibles. (case vide)
	private boolean isValidValue(int r) {
		return (r >= 0 && r <= 9);
	}

}
