package isep.projet.graphic;

import isep.projet.app.GraphicPreGame;
import isep.projet.app.Main;
import isep.projet.sixcouleurs.Button;
import isep.projet.sixcouleurs.Letters;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class LauncherHud {
	
	private Main m;
	private GraphicPreGame gpg;
	private Letters letters=new Letters();
	
	private Button[] buttons;
	
	private SpriteSheet alphabet;
	private SpriteSheet simpleModele;
	private SpriteSheet basicModele;
	private SpriteSheet selectSquare;
	private SpriteSheet selectedSquare;
	private SpriteSheet selectedSquare2;
	private SpriteSheet selectedSquare3;
	private SpriteSheet start;
	private SpriteSheet lightSimple;
	private SpriteSheet lightBasic;
	private SpriteSheet shadowSimple;
	private SpriteSheet shadowBasic;
	
	public void init(Main m,GraphicPreGame gpg) throws SlickException{

		this.start=new SpriteSheet("src/isep/resources/start.png", 54, 18);
		this.alphabet=new SpriteSheet("src/isep/resources/alphabet.png",8,8);
		this.simpleModele=new SpriteSheet("src/isep/resources/SimpleModele.png",32,32);
		this.basicModele=new SpriteSheet("src/isep/resources/BasicModele.png",32,32);
		this.selectSquare=new SpriteSheet("src/isep/resources/selectSquare.png",8,8);
		this.selectedSquare=new SpriteSheet("src/isep/resources/selectedSquare.png",8,8);
		this.selectedSquare2=new SpriteSheet("src/isep/resources/selectedSquare2.png",8,8);
		this.selectedSquare3=new SpriteSheet("src/isep/resources/selectedSquare3.png",14,14);
		this.lightSimple=new SpriteSheet("src/isep/resources/SimpleTheme/light.png",32,32);
		this.shadowSimple=new SpriteSheet("src/isep/resources/SimpleTheme/shadow.png",32,32);
		this.lightBasic=new SpriteSheet("src/isep/resources/BasicTheme/light.png",32,32);
		this.shadowBasic=new SpriteSheet("src/isep/resources/BasicTheme/shadow.png",32,32);
		
		this.gpg=gpg;
		this.m=m;
		this.buttons=new Button[60];
		
		for (int i=0;i<buttons.length;i++){										//all *3 cause game is scaled x3
			this.buttons[i]=new Button();
			if (i<3){
				this.buttons[i].init(3*20+(i*3*58), 3*8, 3*30, 3*8, i);
			} else if (i<7){
				this.buttons[i].init(3*20+(3*(i-3)*58), 3*8, 3*70, 3*8, i);
			} else if (i<32){
				this.buttons[i].init((3*(i-7)*8)+(3*10), 3*8, 3*105, 3*8, i);
			} else if (i<57){
				this.buttons[i].init((3*(i-32)*8)+(3*10), 3*8, 3*145, 3*8, i);
			} else if (i<59){
				this.buttons[i].init(3*20+(3*(i-57)*80)+3*3, 3*27, 3*190+3*3,3* 27, i);
			}else{
				this.buttons[i].init(3*160, 3*54, 3*197, 3*18, i);
			}
			
		}
		
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.white);
		g.fillRect(0,0,700,700);
		
		int[][] textStack=new int[5][];
		
		textStack[0]=this.letters.getTabIndex("Nombre de joueurs: ");
		textStack[1]=this.letters.getTabIndex("Nombre d'ordinateurs: ");
		textStack[2]=this.letters.getTabIndex("Longueur du plateau: ");
		textStack[3]=this.letters.getTabIndex("Hauteur du plateau: ");
		textStack[4]=this.letters.getTabIndex("Choix du thème: ");
		
		g.scale(3f, 3f);
		
		for (int i=0;i<textStack.length;i++){
			
			for (int j=0;j<textStack[i].length;j++){
				g.drawImage(this.alphabet.getSprite(textStack[i][j], 0),(float)(j*8)+10, (float)(i*40)+10);
			}
			
		}
		
		for (int j=0;j<2;j++){
			for (int i=0;i<25;i++){
				g.drawImage(this.selectSquare,(float)(i*8)+10, (float)105+(j*40));
			}
		}
		
		for (int i=0;i<=this.m.getxSize();i++){
			g.drawImage(this.selectedSquare,(float)(i*8)+10, (float)105);
		}
		
		for (int i=0;i<=this.m.getySize();i++){
			g.drawImage(this.selectedSquare2,(float)(i*8)+10, (float)105+40);
		}
		
		g.drawImage(this.simpleModele,(float)20, (float)190);
		g.drawImage(this.basicModele,(float)100, (float)190);
		
		if (m.getTheme()=="SimpleTheme"){
			g.drawImage(this.lightSimple,(float)20, (float)190);
			g.drawImage(this.shadowBasic,(float)100, (float)190);
		}
		else{
			g.drawImage(this.shadowSimple,(float)20, (float)190);
			g.drawImage(this.lightBasic,(float)100, (float)190);
		}
		
		
		g.drawImage(this.start, 160, 197);
		
		for (int i=0;i<3;i++){
			g.drawImage(this.selectSquare,20+(i*58),30);
			g.drawImage(this.alphabet.getSprite(this.letters.getTabIndex(String.valueOf(i+2))[0],0), 20+(i*58)+12,30);
			if (i+2==this.m.getPlayerNumber()) g.drawImage(this.selectedSquare3,20+(i*58)-1,30-3);
		}
		
		for (int i=0;i<4;i++){
			g.drawImage(this.selectSquare,20+(i*58),70);
			g.drawImage(this.alphabet.getSprite(this.letters.getTabIndex(String.valueOf(i))[0],0), 20+(i*58)+12,70);
			if (i==this.m.getCpuNumber()) g.drawImage(this.selectedSquare3,20+(i*58)-1,70-3);
		}
		
	}

	public void mousePressed(int button, int x, int y){
		
		for (Button b:buttons){
			b.update(x, y);
			if (b.isPressed()) {
				
				if (b.getValue()<3){
					this.m.setPlayerNumber(b.getValue()+2);
				} else if (b.getValue()<7){
					this.m.setCpuNumber(b.getValue()-3);
				} else if (b.getValue()<32){
					this.m.setxSize(b.getValue()-7);
				} else if (b.getValue()<57){
					this.m.setySize(b.getValue()-32);
				} else if (b.getValue()<59){
					this.m.setTheme(b.getValue()-57);
				}else{
					this.gpg.destroyLauncher();
				}
				
				b.setPressed(false);
			}
		}
		
	}
	
	public SpriteSheet[] getSpriteSheet(){
		SpriteSheet[] sprites={this.alphabet,this.lightBasic,this.lightSimple,this.shadowBasic,this.shadowSimple};
		return sprites;
	}
	
}
