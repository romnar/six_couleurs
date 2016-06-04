package isep.projet.app;

import isep.projet.graphic.LauncherHud;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class GraphicPreGame extends BasicGame{
	
	private boolean exit=false;
	
	private Main m;
	private LauncherHud launcherHud=new LauncherHud();
	
	public GraphicPreGame(Main m) {
		
		super("6 couleurs Launcher");
		this.m=m;
		
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		this.launcherHud.render(g);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.launcherHud.init(m,this);
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {

		if (this.exit){
			
			container.setForceExit(false);
			container.exit();
		
		}
		
	}

	@Override
	public void mousePressed(int button,int x,int y){
		
		this.launcherHud.mousePressed(button,x,y);
		
	}
	
	public void destroyLauncher(){
		
		try {
			for (SpriteSheet s:this.launcherHud.getSpriteSheet())
				s.destroy();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.exit=true;
		
	}
	
}
