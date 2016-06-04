package isep.projet.sixcouleurs;

public class ArtificialIntelligence {
	
	private int whichAI;
	
	public void init(int whichAI){
		
		this.whichAI=whichAI;
		
	}

	public int play(){
		
		switch(this.whichAI){
		case 0: return randomPlay();
		}
		return 0;
	}
	
	private int randomPlay(){
		
		return (int)(Math.random()*6);
		
	}
	
}
