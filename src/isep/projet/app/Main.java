package isep.projet.app;

import isep.projet.graphic.Hud;
import isep.projet.graphic.Joypad;
import isep.projet.sixcouleurs.ArtificialIntelligence;
import isep.projet.sixcouleurs.Game;
import isep.projet.sixcouleurs.Player;
import isep.projet.sixcouleurs.PlayingEntities;
import isep.projet.sixcouleurs.Tokens;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame{

	private PlayingEntities playingEntities=new PlayingEntities();
	private ArtificialIntelligence aI=new ArtificialIntelligence();
	private Tokens tokens=new Tokens();
	private Joypad joypad=new Joypad();
	private Hud hud=new Hud();
	private Game game;
	
	private int xSize;
	private int ySize;
	private int playerNumber;
	private int cpuNumber;
	private int boardXSize;
	private int boardYSize;
	
	private String theme;
	
	public Main() {
		
		super("6 couleurs");
		this.xSize=13;
		this.ySize=13;
		this.playerNumber=2;
		this.cpuNumber=1;
		this.theme="BasicTheme";
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		this.hud.render(g);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		container.setShowFPS(false);
		this.aI.init(0);
		this.game=new Game();
		this.tokens.init(xSize,ySize,playerNumber);
		this.playingEntities.init(playerNumber,game,tokens,cpuNumber);
		this.hud.init(this.playingEntities, this.joypad,this.tokens,this.xSize,this.ySize,this.boardXSize,this.boardYSize,this.theme);
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		if (this.playingEntities.getActivePlayer().isCpu()) this.joypad.setActivatedButton(this.aI.play());
		
		if (this.joypad.getActivatedButton()!=-1){
			if (this.joypad.isPlayable(this.playingEntities)){
				
				this.game.play(this.playingEntities.getActivePlayer(), this.joypad.getActivatedButton(), this.tokens, this.playingEntities);
				if (this.game.victoryTest(this.playingEntities,this.xSize,this.ySize)){
					Player winner[]={this.playingEntities.getActivePlayer()};
					this.hud.setWinner(winner);
					
				}
				this.playingEntities.nextPlayer();
				
			}
			
			this.joypad.setActivatedButton(-1);
			if (this.playingEntities.hasGameEnd(this.xSize*this.ySize)){
				this.hud.setWinner(this.playingEntities.getWinners());
			}
			
		}
		
	}
	
	@Override
	public void mousePressed(int button,int x,int y){

		this.joypad.update(x,y);
		
	}
	
	@Override
	public void mouseReleased(int button,int x,int y){
		
		this.joypad.releaseButtons();
		
	}
	
	public static void main(String [] args) throws SlickException {
		
		Main m=new Main();
		PreGame pG=new PreGame();
		pG.init(m);
			
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		
		if (xSize<5) this.xSize=5;
		else this.xSize=xSize; 
	
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {

		if (ySize<5) this.ySize=5;
		else this.ySize=ySize; 
		
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		
		this.playerNumber = playerNumber;
		if (this.playerNumber<=this.cpuNumber) this.cpuNumber=this.playerNumber-1;
		
	}

	public int getBoardXSize() {
		return boardXSize;
	}

	public void setBoardXSize(int boardXSize) {
		this.boardXSize = boardXSize-(boardXSize%32)+32;
	}

	public int getBoardYSize() {
		return boardYSize;
	}

	public void setBoardYSize(int boardYSize) {
		this.boardYSize = boardYSize-(boardYSize%32)+32;
	}

	public void setCpuNumber(int cpuNumber) {
		
		if (cpuNumber>=this.playerNumber) this.cpuNumber=this.playerNumber-1;
		else this.cpuNumber=cpuNumber;
		
	}
	
	public int getCpuNumber(){
		return this.cpuNumber;
	}
	
	public void setTheme(int theme){
		
		if (theme==0) this.theme="SimpleTheme";
		else this.theme="BasicTheme";
		
	}
	
	public String getTheme(){
		return this.theme;
	}
	
}
