package isep.projet.graphic;

import isep.projet.sixcouleurs.Player;
import isep.projet.sixcouleurs.PlayingEntities;
import isep.projet.sixcouleurs.Tokens;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Hud {
		
	private PlayingEntities playingEntities;
	private Joypad joypad;
	private VictoryScreen victoryScreen=new VictoryScreen();
	private InfoGame infoGame=new InfoGame();
	private Board board=new Board();
	private Player[] winners;

	private SpriteSheet spriteSheetPressedGems;
	private SpriteSheet spriteSheetWindow;
	private SpriteSheet spriteSheetShadow;
	private SpriteSheet spriteSheetShadow2;
	private SpriteSheet spriteSheetGems;
	private SpriteSheet greySquare;
	private SpriteSheet alphabet;
	private SpriteSheet light;
	//private SpriteSheet wallPaper;
	private SpriteSheet gameWindow;
	
	private boolean gameWon=false;
	private int boardXSize;
	private int boardYSize;
	
	public void init(PlayingEntities playingEntities, Joypad joypad, Tokens tokens, int xSize, int ySize, int boardXSize, int boardYSize, String theme) throws SlickException{
		
		this.spriteSheetPressedGems = new SpriteSheet("src/isep/resources/"+theme+"/pressedGems.png",32,32);
		this.spriteSheetWindow = new SpriteSheet("src/isep/resources/"+theme+"/windows.png", 16, 16);
		this.spriteSheetShadow = new SpriteSheet("src/isep/resources/"+theme+"/shadow.png",32,32);
		this.spriteSheetShadow2 = new SpriteSheet("src/isep/resources/"+theme+"/shadow2.png",32,32);
		this.spriteSheetGems = new SpriteSheet("src/isep/resources/"+theme+"/gems.png", 32, 32);
		this.light = new SpriteSheet("src/isep/resources/"+theme+"/light.png",32,32);
		this.greySquare = new SpriteSheet("src/isep/resources/greySquare.png", 1, 1);
		this.alphabet = new SpriteSheet("src/isep/resources/alphabet.png",8,8);
		//this.wallPaper = new SpriteSheet("src/isep/resources/wallpaper.png",1,1);
		this.gameWindow=new SpriteSheet("src/isep/resources/window.png", 8, 8);
		
		this.playingEntities=playingEntities;
		this.joypad=joypad;
		
		this.board.init(xSize, ySize, boardXSize, boardYSize, tokens, this, this.spriteSheetWindow, this.spriteSheetGems,this.spriteSheetShadow2,this.light,this.gameWindow);
		this.infoGame.init(this.playingEntities, this.spriteSheetWindow, this.spriteSheetGems, this.alphabet,xSize,ySize,boardXSize);
		this.joypad.init(xSize,this.spriteSheetWindow,this.spriteSheetGems,this.spriteSheetPressedGems,this.spriteSheetShadow);
		this.victoryScreen.init(this.greySquare, this.alphabet, boardXSize, boardYSize);

		this.boardXSize=boardXSize;
		this.boardYSize=boardYSize;
		
	}
	
	public void render(Graphics g){
		
		//g.drawImage(this.wallPaper, 0, 0);
		
		this.board.render(g);
		this.joypad.render(g, this.playingEntities);
		this.infoGame.render(g);
		if (this.gameWon)
			this.victoryScreen.render(this.winners, g, this.boardXSize, this.boardYSize);
		
	}

	public void setWinner(Player[] winners) {
		this.winners = winners;
		this.gameWon=true;
	}
	
}
