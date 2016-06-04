package isep.projet.graphic;

import isep.projet.sixcouleurs.Tokens;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Board {

	private Tokens tokens;
	private SpriteSheet spriteSheetWindow;
	private SpriteSheet spriteSheetGems;
	private SpriteSheet spriteSheetShadow2;
	private SpriteSheet light;
	private SpriteSheet gameWindow;
	
	private int[][] window;
	private int[][] window2;
	private int xSize;
	private int ySize;

	public void init(int xSize,int ySize,int boardXSize,int boardYSize,Tokens tokens,Hud hud,SpriteSheet spriteSheetWindow,SpriteSheet spriteSheetGems,SpriteSheet spriteSheetShadow2,SpriteSheet light,SpriteSheet gameWindow) 
			throws SlickException{
		
		this.gameWindow=gameWindow;
		this.spriteSheetWindow = spriteSheetWindow;
		this.spriteSheetGems = spriteSheetGems;
		this.spriteSheetShadow2 = spriteSheetShadow2;
		this.light = light;
		
		this.setSize(xSize, ySize);
		this.window=new int[this.xSize][this.ySize];
		this.window2=new int[(boardXSize/32)][(boardYSize/32)];
		this.create(this.window,this.xSize,this.ySize);
		this.create(window2, (boardXSize/32), (boardYSize/32));
		
		this.tokens=tokens;
		
	}
	
	private void setSize(int x,int y){
		
		if (x<5) this.xSize=6;
		else if (x>25) this.xSize=26;
		else this.xSize=x+1;
		
		if (y<5) this.ySize=6;
		else if (y>25) this.ySize=26;
		else this.ySize=y+1;
		
	}
	
	private void create(int[][] window,int xSize, int ySize){
		
		window[0][0]=3;
		window[xSize-1][0]=4;
		window[0][ySize-1]=1;
		window[xSize-1][ySize-1]=2;
		
		for (int i=0;i<xSize-2;i++){
			window[i+1][0]=6;
			window[i+1][ySize-1]=5;
		}
		
		for (int i=0;i<ySize-2;i++){
			window[0][i+1]=7;
			window[xSize-1][i+1]=8;
		}
		
	}
	
	public void render(Graphics g){
		
		g.scale(4f,4f);

		for (int i=0;i<this.window2.length;i++)
			for (int j=0;j<this.window2[i].length;j++){
				g.drawImage(gameWindow.getSprite(this.window2[i][j], 0),(float)((i*8)), (float)((j*8)));
			}

		g.resetTransform();
		g.scale(2f,2f);
		
		//g.setColor(Color.white);
		//g.fillRect(0,0,1470,1000);
		
		for (int i=0;i<this.xSize;i++)
			for (int j=0;j<this.ySize;j++){
				if (i!=xSize-1&&j!=ySize-1){
					g.drawImage(spriteSheetGems.getSprite(this.tokens.getCase(i, j).getColor(), 0),(float)((i*16)+10), (float)((j*16)+20+18));
					if (this.tokens.getCase(i, j).getJoueur()==null) g.drawImage(spriteSheetShadow2,(float)((i*16)+10), (float)((j*16)+20+18));
					else g.drawImage(light,(float)((i*16)+10), (float)((j*16)+20+18));
				}
				g.drawImage(spriteSheetWindow.getSprite(this.window[i][j], 0),(float)((i*16)+10), (float)((j*16)+20+18));
			}
	}
	
}
