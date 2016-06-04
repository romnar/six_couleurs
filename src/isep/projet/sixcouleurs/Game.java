package isep.projet.sixcouleurs;  

public class Game {
	
	public void init(Player player,Tokens tokens){
		
		analyseAdj(player.getXPos()*(tokens.getxSize()-1),player.getYPos()*(tokens.getySize()-1),player.getColor(),player.getNumPlayer(),tokens,player);
		
	}
	
	public void play(Player activePlayer, int color, Tokens tokens,PlayingEntities playingEntities) {
		
		int xP = activePlayer.getXPos() * (tokens.getxSize()-1);
		int yP = activePlayer.getYPos() * (tokens.getySize()-1);
		
		tokens.getTokens()[xP][yP].setColor(color);              	//on remplace la couleur de la case de depart
		//tokens.getTokens()[xP][yP].setMajuscule(color);				//on mémorise l’ancienne couleur de la case de depart
		adj(xP,yP,color,activePlayer.getNumPlayer()-1,tokens,activePlayer);             			//on explore les cases adjacentes pour trouver les couleurs à remplacer	   
		activePlayer.setColor(color);

	}
	
	private void adj(int xP, int yP, int newColor, int numPlayer,Tokens tokens,Player activePlayer) {
		
		if (xP > 0)
			analyseAdj(xP - 1, yP, newColor,numPlayer,tokens,activePlayer);
		if (xP+1 < tokens.getxSize())
			analyseAdj(xP + 1, yP, newColor,numPlayer,tokens,activePlayer);
		if (yP > 0)
			analyseAdj(xP, yP - 1, newColor,numPlayer,tokens,activePlayer);
		if (yP+1 < tokens.getySize())
			analyseAdj(xP, yP + 1, newColor,numPlayer,tokens,activePlayer);
		
	}
	
	private void analyseAdj(int x, int y, int newColor, int numPlayer, Tokens tokens,Player activePlayer) {
		
		if (tokens.getTokens()[x][y].getJoueur() == null && tokens.getTokens()[x][y].getColor() == newColor) {
			
			tokens.getTokens()[x][y].setColor(newColor);
			tokens.getTokens()[x][y].setJoueur(activePlayer);
			activePlayer.incrNbCase();
			adj(x, y, newColor,numPlayer,tokens,activePlayer);
			
		}
		
		else if(tokens.getTokens()[x][y].getJoueur() == activePlayer && tokens.getTokens()[x][y].getColor()!=newColor){
			
			tokens.getTokens()[x][y].setColor(newColor);
			adj(x, y, newColor,numPlayer,tokens,activePlayer);
			
		}
		
	}
	
	public boolean victoryTest(PlayingEntities pE,int xSize,int ySize){
		
		Player bestAfterActive=pE.getBestAfterActive();
		int waste=0;
		int totalCase=xSize*ySize;
		
		for (Player p:pE.getPlayers()){
			if (p!=pE.getActivePlayer() && p!=bestAfterActive) waste+=p.getNbCase();
		}
		
		
		
		if (pE.getActivePlayer().getNbCase()>totalCase-waste-pE.getActivePlayer().getNbCase()) return true;
		return false;
		
	}

}