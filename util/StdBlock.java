package util;

public class StdBlock implements Block {
	// ATTRIBUTS
	//tableau de cases
	private Case[][] tab;
	
	// CONSTRUCTEUR	
	public StdBlock(Case[][] tab) {
		this.tab = tab;
	}
	
	// REQUETES
	public Case getCase(int r, int c) {
		return tab[r][c];
	}
	
	public boolean isValid() {
		int[] blockValuesNb = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!isValidValue(tab[i][j].getValue())) {
					return false;
				}
			}
		}
		for (int currentValue = 1; currentValue < 10; currentValue++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tab[i][j].getValue() == currentValue) {
						blockValuesNb[currentValue - 1] += 1;
					}
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			if (blockValuesNb[i] > 1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isFull() {
		int[] blockValuesNb = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		for (int currentValue = 1; currentValue < 10; currentValue++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tab[i][j].getValue() == currentValue) {
						blockValuesNb[currentValue - 1] += 1;
					}
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			if (blockValuesNb[i] != 1) {
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
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tab[i][j].addPossibleCandidate(v);
			}
		}
	}
	
	public void removePossibleCandidates(int v) {
		if (!isValidValue(v) || v == 0) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tab[i][j].removePossibleCandidate(v);
			}
		}
	}
	
	public void setCase(int r, int c, int v) {
		if (r < 0 || r > 3 || c < 0 || c > 3) {
			throw new IllegalArgumentException("Mauvais numéro de colonne");
		}
		if (!isValidValue(v)) {
			throw new IllegalArgumentException("Mauvaise valeur");
		}
		tab[r][c].setValue(v);
	}
	
	// OUTILS
	private boolean isValidValue(int v) {
		return v >= 0 && v <= 9;
	}
}
