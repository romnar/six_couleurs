package isep.projet.graphic;

import isep.projet.sixcouleurs.Letters;
import isep.projet.sixcouleurs.PlayingEntities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class InfoGame {
	
	private PlayingEntities playingEntities;
	private Letters letters=new Letters();
	
	private SpriteSheet spriteSheetWindow;
	private SpriteSheet spriteSheetGems;
	private SpriteSheet alphabet;
	
	private int[] whoPlaysInt;
	private int[] playerInfos;
	
	private String whoPlays="Tour de";
	private String player="Joueur ";
	private String cpu="C.P.U. ";
	
	private int xSize;
	private int ySize;
	private int boardXSize;
	
	private int window[][]={{3,1},{4,2}};

	public void init(PlayingEntities playingEntities,SpriteSheet spriteSheetWindow,SpriteSheet spriteSheetGems,SpriteSheet alphabet,int xSize,int ySize,int boardXSize) throws SlickException{
		
		whoPlaysInt=this.letters.getTabIndex(whoPlays);
		
		this.spriteSheetWindow = spriteSheetWindow;
		this.spriteSheetGems = spriteSheetGems;
		this.alphabet = alphabet;
		
		this.playingEntities=playingEntities;
		
		this.xSize=xSize;
		this.ySize=ySize;
		this.boardXSize=boardXSize;
		
	}

	public void render(Graphics g){
		
		this.renderActivePlayer(g);
		this.renderPlayerInfos(g);
		
	}
	
	private void renderActivePlayer(Graphics g){
		
		for (int i=0;i<whoPlaysInt.length;i++){
			g.drawImage(this.alphabet.getSprite(this.whoPlaysInt[i], 0),(float)((i*8)+10), 21f);
		}
		
		this.renderSquare(g, 70, 10, this.playingEntities.getActivePlayer().getColor());
		
	}
	
	private void renderPlayerInfos(Graphics g){
		
		for (int i=0;i<this.playingEntities.getPlayers().length;i++){
			
			int percentage=(int)(100*this.playingEntities.getPlayers()[i].getNbCase())/(this.xSize*this.ySize);
			
			String toPrint;
			if (!this.playingEntities.getPlayers()[i].isCpu())
				toPrint=this.player+String.valueOf(this.playingEntities.getPlayers()[i].getNumPlayer())+" : "+String.valueOf(percentage)+"%";
			else 
				toPrint=this.cpu+String.valueOf(this.playingEntities.getPlayers()[i].getNumPlayer())+" : "+String.valueOf(percentage)+"%";
			
			
			this.playerInfos=this.letters.getTabIndex(toPrint);
			
			for (int j=0;j<this.playerInfos.length;j++)
				g.drawImage(this.alphabet.getSprite(this.playerInfos[j], 0),(float)(j*8)+((this.boardXSize/2)-130), (float)(i*32)+36);
		
			this.renderSquare(g, (float)((this.boardXSize/2)-162), (float)(i*32)+24, this.playingEntities.getPlayers()[i].getColor());
			
		}
		
	}
	
	private void renderSquare(Graphics g, float xPos, float yPos, int color){
		
		g.drawImage(this.spriteSheetGems.getSprite(color, 0),xPos, yPos);
		
		for (int j=0;j<2;j++){
			for (int i=0;i<2;i++){
				g.drawImage(this.spriteSheetWindow.getSprite(this.window[i][j], 0),(float)((i*16)+xPos), (float)((j*16)+yPos));
			}
		}
		
	}
	
}
