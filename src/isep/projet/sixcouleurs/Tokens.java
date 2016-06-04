package isep.projet.sixcouleurs;

import java.util.Random;

public class Tokens {

	public Case[][] tokens;
	private int xSize;
	private int ySize;
	
	public void init(int xSize,int ySize,int playerNumber){
		
		this.setSize(xSize, ySize);
		this.tokens=new Case[this.xSize][this.ySize];
		this.fillBoard(xSize, ySize);
		this.setPlayersColor(xSize, ySize, playerNumber);
		
	}
	
	public Case getCase(int xPos,int yPos){
		
		return tokens[xPos][yPos];
		
	}
	
	private void setSize(int x,int y){
		
		if (x<5) this.xSize=5;
		else if (x>25) this.xSize=25;
		else this.xSize=x;
		
		if (y<5) this.ySize=5;
		else if (y>25) this.ySize=25;
		else this.ySize=y;
		
	}
	
	private void fillBoard(int xSize, int ySize){
		
		Random r = new Random();
		
		for (int j=0;j<ySize;j++){
			for (int i=0;i<xSize;i++){
				tokens[i][j] = new Case();
				tokens[i][j].init(r.nextInt(6));
			}
		}
		
	}
	
	private void setPlayersColor(int xSize, int ySize, int playerNumber){
		
		this.tokens[0][0].setColor(0);
		this.tokens[xSize-1][ySize-1].setColor(1);
		if (playerNumber>2) this.tokens[xSize-1][0].setColor(2);
		if (playerNumber>3) this.tokens[0][ySize-1].setColor(3);
		
	}

	public Case[][] getTokens() {
		return tokens;
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}
	
}
