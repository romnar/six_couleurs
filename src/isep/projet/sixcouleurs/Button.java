package isep.projet.sixcouleurs;

public class Button {

	private int xStartPos;
	private int xStopPos;
	
	private int yStartPos;
	private int yStopPos;
	
	private int value;
	
	private boolean isPressed=false;
	
	public void init(int xStartPos,int xLength,int yStartPos,int yLength, int buttonNumber){
		
		this.xStartPos=xStartPos;
		this.xStopPos=this.xStartPos+xLength;
		
		this.yStartPos=yStartPos;
		this.yStopPos=this.yStartPos+yLength;
		
		this.value=buttonNumber;
		
	}
	
	public void update(float x,float y){
		
		//System.out.println(this.xStartPos+"   "+x+"   "+this.xStopPos+"______________________"+this.yStartPos+"   "+y+"   "+this.yStopPos);
		
		if (this.xStartPos<=x&&this.xStopPos>=x &&					
				this.yStartPos<=y&&this.yStopPos>=y){
			this.isPressed=true;
		}
		
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public int getValue() {
		return value;
	}
	
}
