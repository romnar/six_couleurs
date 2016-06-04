package isep.projet.sixcouleurs;

public class Case {
	private int color;
	private Player joueur;
	
	public Case() {
		
		this.color = - 1;
		joueur=null;
		
	}
	
	public void init(int color){
		
		this.color=color;
				
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public Player getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Player j) {
		this.joueur = j;
	}
}
