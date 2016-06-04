package isep.projet.graphic;

import isep.projet.sixcouleurs.Button;
import isep.projet.sixcouleurs.Player;
import isep.projet.sixcouleurs.PlayingEntities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Joypad {
	
	private SpriteSheet spriteSheetWindow;
	private SpriteSheet spriteSheetGems;
	private SpriteSheet spriteSheetPressedGems;
	private SpriteSheet spriteSheetShadow;
	
	private Button[] buttons=new Button[6];
	
	
	
	private int activatedButton=-1;
	private int xSize;
	
	private int window[][]={
			{3,4},
			{7,8},
			{7,8},
			{7,8},
			{7,8},
			{7,8},
			{1,2}
			};

	public void init (int xSize,SpriteSheet spriteSheetWindow,SpriteSheet spriteSheetGems,SpriteSheet spriteSheetPressedGems,SpriteSheet spriteSheetShadow) throws SlickException{
		
		this.spriteSheetWindow = spriteSheetWindow;
		this.spriteSheetGems = spriteSheetGems;
		this.spriteSheetPressedGems = spriteSheetPressedGems;
		this.spriteSheetShadow = spriteSheetShadow;
		
		for (int i=0;i<buttons.length;i++){
			buttons[i]=new Button();
			buttons[i].init((xSize*16)+10+30+10,12,(i*16)+10+10+14,12,i);
		}
		
		this.xSize=xSize;
		
	}
	
	public void update(int x,int y){
		
			for (Button b:buttons){
				b.update(x/2,y/2);					// /2 cause game scaled by 2
			}
			
			if (this.isUsed()!=-1){
				this.activatedButton=this.isUsed();
			}
		
	}
	
	private int isUsed(){
		
		int ans=-1;
		
		for (Button b:buttons){
			if (b.isPressed()) ans=b.getValue();
		}
		
		return ans;
		
	}
	
	public void releaseButtons(){
		
		for (Button b:buttons) b.setPressed(false);
		
	}
	
	public void render(Graphics g,PlayingEntities pE){
		
		for (int i=0;i<2;i++)
			for (int j=0;j<7;j++){
				if (i==0&&j!=6){
					if (buttons[j].isPressed()) g.drawImage(spriteSheetPressedGems.getSprite(j, 0),(float)((this.xSize*16)+10+30), (float)((j*16)+10+18));
					else g.drawImage(spriteSheetGems.getSprite(j, 0),(float)((this.xSize*16)+10+30), (float)((j*16)+10+18));
					if (!addShadow(j,pE)) g.drawImage(spriteSheetShadow.getSprite(0, 0),(float)((this.xSize*16)+10+30), (float)((j*16)+10+18));
				}
				g.drawImage(spriteSheetWindow.getSprite(this.window[j][i], 0),(float)((i*16)+(this.xSize*16)+10+30), (float)((j*16)+10+18));
			}
		
	}
	
	public boolean addShadow(int color, PlayingEntities pE){
		
		for (Player p:pE.getPlayers()){
			if (p.getColor()==color) return false;
		}
		return true;
		
	}
	
	public boolean isPlayable(PlayingEntities pE){
		
		for (Player p:pE.getPlayers()){
			if (p.getColor()==this.activatedButton) return false;
		}
		return true;
		
	}

	public int getActivatedButton() {
		return activatedButton;
	}

	public void setActivatedButton(int activatedButton) {
		this.activatedButton = activatedButton;
	}
	
}
