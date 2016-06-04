package isep.projet.graphic;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import isep.projet.sixcouleurs.Letters;
import isep.projet.sixcouleurs.Player;

public class VictoryScreen {

	private SpriteSheet greySquare;
	private SpriteSheet alphabet;
	
	private Letters letters=new Letters();
	
	private String startMessage="Joueur";
	private String endMessage;
	
	public void init(SpriteSheet greySquare, SpriteSheet alphabet,int boardXSize,int boardYSize){
		
		this.greySquare=greySquare;
		this.alphabet=alphabet;
		
	}
	
	public void render(Player[] winners,Graphics g,int xBoardSize,int yBoardSize){
		
		String message;
		
		if (winners.length==1){
			message=this.startMessage+" ";
			this.endMessage="a gagné!";
		}
		else{
			message=this.startMessage+"s ";
			this.endMessage="ont gagné!";
		}
		
		
		
		for (int i=0;i<winners.length;i++){
			if (i+1!=winners.length) message+=winners[i].getNumPlayer()+", ";
			else message+=winners[i].getNumPlayer()+" ";
		}
		message+=endMessage;
		int[] winningMessage=this.letters.getTabIndex(message);
		
		g.scale(xBoardSize, yBoardSize);
		g.drawImage(greySquare, 0, 0);
		g.resetTransform();
		g.scale(4f,4f);
		for (int i=0;i<winningMessage.length;i++){
			g.drawImage(this.alphabet.getSprite(winningMessage[i], 0), ((xBoardSize/4)-(winningMessage.length*8))/2+(8*i), (yBoardSize/8)-4);
		}
		
	}
	
}
