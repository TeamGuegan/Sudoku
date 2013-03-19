package util;

public class StdRow implements Row {

	// ATTRIBUTS
	private Case[] row;
	
	// CONSTRUCTEUR
	StdRow(Case[] tab) {
		row = tab;
	}
	
	// REQUETES
	public Case getCase(int col) {
		if (col < 0 || col > 8) {
			throw new IllegalArgumentException("Mauvais num�ro de ligne");
		}
		return row[col];
	}
	
	public boolean isValid() {
		for (int i = 0; i < 8; i++) {
			if (!isValidValue(i)) {
				return false;
			}
			for (int j = i + 1; j < 9; j++) {
				if (row[i].getValue() == row[j].getValue()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isFull() {
		for (int i = 0; i <= 8; i++) {
			if (row[i].getValue() == 0) {
				return false;
			}
		}
		return true;
	}
	
	// COMMANDES
	public void setCase(int col, int v) {
		if (col < 0 || col > 8) {
			throw new IllegalArgumentException("Mauvais num�ro de colonne");
		}
		if (v < 0 || v > 9) {
			throw new IllegalArgumentException("Mauvaise valeur");
		}
		row[col].setValue(v);
	}
	
	// OUTILS
	// 0 inclus dans les valeurs possibles. (case vide)
	private boolean isValidValue(int v) {
		return (v >= 0 && v <= 9);
	}
}
