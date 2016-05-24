package isep.projet.sixcouleurs;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Main extends BasicGame{
	
	private static int nbPlayer;
	
	private Joueur[] joueurs;
	
	private ConsoleGame cG;
	private Game game;
	private SpriteSheet spriteSheetWindow;
	private SpriteSheet spriteSheetGems;
	private int[][] window;
	private int xSize;
	private int ySize;
	private final static int fps=60;
	
	public Main(){
		super("6 couleurs");
	}
	
	public void create(){
		this.window[0][0]=3;
		this.window[this.xSize-1][0]=4;
		this.window[0][this.ySize-1]=1;
		this.window[this.xSize-1][this.ySize-1]=2;
		for (int i=0;i<this.xSize-2;i++){
			this.window[i+1][0]=6;
			this.window[i+1][this.ySize-1]=5;
		}
		for (int i=0;i<this.ySize-2;i++){
			this.window[0][i+1]=7;
			this.window[this.xSize-1][i+1]=8;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.cG.printTab();
		
		/*g.setColor(Color.white);				//AFFICHE LE JEU GRAPHIQUE
		g.fillRect(0,0,956,956);
		g.scale(4f,4f);
		for (int i=0;i<this.xSize;i++)
			for (int j=0;j<this.ySize;j++){
				if (i!=xSize-1&&j!=ySize-1){
					g.drawImage(spriteSheetGems.getSprite(((0)), 0),(float)((i*16)+10), (float)((j*16)+10));
				}
				g.drawImage(spriteSheetWindow.getSprite(this.window[i][j], 0),(float)((i*16)+10), (float)((j*16)+10));
			}*/
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		this.cG=new ConsoleGame(this);
		this.cG.init();
		this.spriteSheetWindow = new SpriteSheet("src/isep/resources/windows.png", 16, 16);
		this.spriteSheetGems = new SpriteSheet("src/isep/resources/gems.png", 32, 32);
		this.window=new int[this.xSize][this.ySize];
		this.game=new Game(this.xSize,this.ySize,this.joueurs);
		this.game.Grille();
		this.game.initGrille();
		this.game.initPlayer(getNbPlayer());
		this.create();
	}
	
	@Override
	public void update(GameContainer container, int alpha) throws SlickException {
		System.out.println("nb Joueurs"+joueurs.length);
		int color;
		for(int i = 1; i <= joueurs.length; i++) {
			color=this.cG.move(i);
			
			while(game.alreadyPlayed(color)) {
				this.cG.colorAlreadyTaken();
				color=this.cG.move(i);
			}
			
			game.play(i,color);
			this.cG.infosPlayer(joueurs[i-1]);
			if (joueurs[i-1].getNbCase() > (xSize * ySize) / 2) {
				this.cG.win(i);
				container.exit();
			}
			this.cG.printTab();
		}
	}

	public static void main(String [] args) throws SlickException {
	//INTERFACE GRAPHIQUE
		
		try {
            AppGameContainer app = new AppGameContainer(new Main(),956,956,false);
            app.setTargetFrameRate(fps);
        	app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}
	
	public int getNbPlayer() {
		return nbPlayer;
	}
	
	
	
	
	
	
	
	
}