package isep.projet.sixcouleurs;  

import isep.projet.sixcouleurs.Case;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private static  Case[][] grille;
	
	private Joueur[] joueurs;
	
	private static int xSize;
	private static int ySize;
	
	Game(int xSize, int ySize, Joueur[] joueurs) {
		Game.xSize = xSize;
		Game.ySize = ySize;
		this.joueurs=joueurs;
	}
	
	public void Grille() {
		Case c;
		int color;
		Random r = new Random();
		grille = new Case[xSize][ySize];
		for (int j=0;j<ySize;j++){
			for (int i=0;i<xSize;i++){
				c = new Case();
				color = r.nextInt(6);
				c.setColor(color);
				c.setMinuscule(color);
				grille[i][j] = c;
			}
		}
	}

	public void initGrille() {										
		for (int j=0;j<ySize;j++){
			for (int i=0;i<xSize;i++){
				grille[i][j].setJoueur(null);
			}
		}
	}
	
	public void test(int i, int x, int y) {
		joueurs[i].setColor(grille[x][y].getColor());
		grille[x][y].setMajuscule(joueurs[i].getColor());
		analyseAdj(x, y, joueurs[i].getColor(),1);
	}
	
	public void initPlayer(int nbPlayer) {
		joueurs[0].setColor(grille[0][0].getColor());
		grille[0][0].setMajuscule(joueurs[0].getColor());
		analyseAdj(0, 0, joueurs[0].getColor(),1);
		joueurs[1].setColor(grille[xSize - 1][ySize - 1].getColor());
		grille[xSize - 1][ySize - 1].setMajuscule(joueurs[1].getColor());
		analyseAdj(xSize - 1, ySize - 1, joueurs[1].getColor(),2);
		if(nbPlayer > 2) {
			joueurs[2].setColor(grille[xSize - 1][0].getColor());
			grille[xSize - 1][0].setMajuscule(joueurs[2].getColor());
			analyseAdj(xSize - 1, 0, joueurs[1].getColor(),3);
		}
		if(nbPlayer > 3) {
			joueurs[3].setColor(grille[0][ySize - 1].getColor());
			grille[0][ySize - 1].setMajuscule(joueurs[3].getColor());
			analyseAdj(0, ySize - 1, joueurs[1].getColor(),4);
		}
	}
	
	public void play(int numPlayer, int color) {								//TODO changer les "if (numPlayer == x)" par une fonction de Joueur qui renvoie les coordonnées de la case de départ de chaque joueur
		
		int xP = 0,yP = 0 /*oldColor = 0*/;
		if (numPlayer == 1) {
			xP = 0;
			yP = 0;			
		}
	   
	   else if (numPlayer == 2) {
		   xP = xSize - 1;
		   yP = ySize - 1;
	   }
	  
	   else if (numPlayer == 3) {
		   xP = xSize - 1;
		   yP = 0;
	   }
	   
	   else if (numPlayer == 4) {
	      xP = 0;
	      yP = ySize - 1;
	   }
	   
	  // oldColor = grille[xP][yP].getColor();
	   grille[xP][yP].setColor(color);              	//on remplace la couleur de la case de depart
	   grille[xP][yP].setMajuscule(color);				//on mémorise l’ancienne couleur de la case de depart
	   adj(xP,yP,color,numPlayer);             			//on explore les cases adjacentes pour trouver les couleurs à remplacer	   
	   joueurs[numPlayer - 1].setColor(color);
	   if (joueurs[numPlayer-1].getNbCase() == 0) joueurs[numPlayer-1].incrNbCase();
	} 
	
	private void adj(int xP, int yP, int newColor, int numPlayer) {
		if (xP > 0)
			analyseAdj(xP - 1, yP, newColor,numPlayer);
		if (xP+1 < xSize)
			analyseAdj(xP + 1, yP, newColor,numPlayer);
		if (yP > 0)
			analyseAdj(xP, yP - 1, newColor,numPlayer);
		if (yP+1 < ySize)
			analyseAdj(xP, yP + 1, newColor,numPlayer);
	}
	
	private void analyseAdj(int x, int y, int newColor, int numPlayer) {
		if (grille[x][y].getJoueur() == null && grille[x][y].getColor() == newColor) {
				grille[x][y].setColor(newColor); 
				grille[x][y].setMajuscule(newColor);
				grille[x][y].setJoueur(joueurs[numPlayer-1]);		
				joueurs[numPlayer-1].incrNbCase();
				adj(x, y, newColor,numPlayer);
		}
		
		
		else if(grille[x][y].getJoueur() == joueurs[numPlayer-1] && grille[x][y].getColor()!=newColor){
			grille[x][y].setColor(newColor);
			grille[x][y].setMajuscule(newColor);
			adj(x, y, newColor,numPlayer);
			
		}		
	}	

	public boolean alreadyPlayed(int color) {
		boolean b = false;
		for (int i=0;i< joueurs.length && !b;i++) {
			if (joueurs[i].getColor() == color) {
				b = true;
			}
		}
		return b;
	}

	public static Case[][] getGrille() {
		return grille;
	}

	public static void setGrille(Case[][] grille) {
		Game.grille = grille;
	}

	public static int getxSize() {
		return xSize;
	}

	public static void setxSize(int xSize) {
		Game.xSize = xSize;
	}

	public static int getySize() {
		return ySize;
	}

	public static void setySize(int ySize) {
		Game.ySize = ySize;
	}
}