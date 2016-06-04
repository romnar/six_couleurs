package isep.projet.app;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class PreGame {

	private AppGameContainer app;
	private AppGameContainer app2;
	
	public void init(Main m){
		this.startLauncher(m);
		
		this.startGame(m);
		
	}
	
	private void startLauncher(Main m){
		
		GraphicPreGame gpg=new GraphicPreGame(m);
		
		try {
			this.app = 
					new AppGameContainer(gpg,
							700,
							700,
	            			false);
	        this.app.setTargetFrameRate(20);
	       	this.app.start();
	    } catch (SlickException e) {
	        e.printStackTrace();
	    }
		
	}
	
	private void startGame(Main m){
		
		m.setBoardXSize((10+10+((1+m.getxSize())*16)+52+162)	*2);
		m.setBoardYSize(Math.max(
				(18+10+10+((1+m.getySize())*16))		*2,
				(18+10+10+((1+6)			*16))		*2));
		
		try {
			this.app2 = 
					new AppGameContainer(m,
							m.getBoardXSize(),
							m.getBoardYSize(),
							false);
			this.app2.setTargetFrameRate(20);
			this.app2.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
}
	

