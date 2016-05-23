package isep.projet.sixcouleurs;

public class Case {
	private int color;
	private Joueur joueur;
	private char value;
	private int[] tab = {114,97,116,103,107,100};
	
	Case() {
		this.color = - 1;

		joueur=null;
		value = 0;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Joueur j) {
		this.joueur = j;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}
	
	public void setMajuscule(int color) {
		this.value = (char)(color + tab[color]-32); // code ascii de 
	}
	
	public void setMinuscule(int color) {
		this.value = (char)(color + tab[color]);
	}
	
	public int getMajuscule(char color) {
		return (int)(color - tab[color]-32);			
	}
	
	public int getMinuscule(char color) {
		return (int)(color - tab[color]);
	}
}
