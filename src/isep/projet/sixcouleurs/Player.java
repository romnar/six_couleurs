package isep.projet.sixcouleurs;

public class Player {
	
	private int numPlayer;
	private int nbCase=0;
	private int color;
	
	private boolean isCpu;
	
	public void init(int numPlayer, boolean isCpu) {
		
		this.isCpu=isCpu;
		this.numPlayer = numPlayer+1;
		this.color=numPlayer;
		
	}
	
	public int getXPos(){						//to multiply by board xSize-1
		switch(numPlayer-1){
		case 0: return 0;
		case 1:	return 1;
		case 2:	return 1;
		case 3: return 0;
		}
		return 0;
	}
	
	public int getYPos(){						//to multiply by board ySize-1
		switch(numPlayer-1){
		case 0: return 0;
		case 1:	return 1;
		case 2:	return 0;
		case 3: return 1;
		}
		return 0;
	}

	public int getNbCase() {
		return nbCase;
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

	public boolean isCpu() {
		return isCpu;
	}
	
}
