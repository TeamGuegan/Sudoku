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
		return false;
	}
	
	public boolean isFull() {
		return false;
	}
	
	// COMMANDES
	public void setCase(int r, int c, int v) {
		
	}
	
	// OUTILS
	private boolean isValidValue(int v) {
		return false;
	}
}
