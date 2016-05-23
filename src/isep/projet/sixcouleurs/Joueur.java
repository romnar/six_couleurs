package isep.projet.sixcouleurs;

public class Joueur {
	private int numPlayer;
	private int nbCase;
	private int color;
	
	Joueur(int numPlayer) {
		this.numPlayer = numPlayer;
		this.nbCase = 0;
	}

	public int getNbCase() {
		return nbCase;
	}

	public void setNbCase(int nbCase) {
		this.nbCase = nbCase;
	}
	
	public void incrNbCase() {
		nbCase ++;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getNumPlayer() {
		return numPlayer;
	}

	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}
}
