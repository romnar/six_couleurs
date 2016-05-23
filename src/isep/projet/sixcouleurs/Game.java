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

	public void initGrille() {										//TODO "init" est pour une fonction que l'on lance une unique fois au début du programme. Changer le nom (pour "update" si ça colle) ou changer la fonction
		for (int j=0;j<ySize;j++){
			for (int i=0;i<xSize;i++){
				grille[i][j].setJoueur(null);
			}
		}
		//joueurs[numPlayer-1].setNbCase(0);
	}
	
	public void play(int numPlayer, int color, Joueur[] joueurs) {				//TODO changer les "if (numPlayer == x)" par une fonction de Joueur qui renvoie les coordonnées de la case de départ de chaque joueur
		
		int xP = 0,yP = 0, oldColor = 0;
		//initGrille(numPlayer);
		if (numPlayer == 1) {
		/**
		 * on mémorise la position du joueur 1.
		 */
			xP = 0;
			yP = 0;			
		}
	   
	   else if (numPlayer == 2) {
		   /**
		    * on mémorise la position du joueur 2.
		    */
		   xP = xSize - 1;
		   yP = ySize - 1;
	   }
	  
	   else if (numPlayer == 3) {
		   /**
		    * on mémorise la position du joueur 3.
		    */
		   xP = xSize - 1;
		   yP = 0;
	   }
	   
	   else if (numPlayer == 4) {
	      /**
	       * on mémorise la position du joueur 4.
	       */
	      xP = 0;
	      yP = ySize - 1;
	   }
	   
	   oldColor = grille[xP][yP].getColor();
	   grille[xP][yP].setColor(color);              	//on remplace la couleur de la case de depart
	   grille[xP][yP].setMajuscule(color);//on mémorise l’ancienne couleur de la case de depart
	   adj(xP,yP,oldColor,color,numPlayer);                       //on explore les cases adjacentes pour trouver les couleurs à remplacer	   
	   
	   
	   //compte(numPlayer, xP,yP,color);								//on compte le nombre de case proprietaires du joueur
	   if (joueurs[numPlayer-1].getNbCase() == 0) joueurs[numPlayer-1].incrNbCase();
	} 
	
	private void adj(int xP, int yP, int oldColor, int newColor, int numPlayer) {
		if (xP > 0) {
			/**
			 *  on examine la case de gauche si elle existe si on est jamais passé et si elle a l'ancienne couleur
			 */
			analyseAdj(xP - 1, yP, oldColor, newColor,numPlayer);
		}
		if (xP+1 < xSize) {
			/**
			 *  on examine la case de droite si elle existe si on est jamais passé et si elle a l'ancienne couleur
			 */
			analyseAdj(xP + 1, yP, oldColor, newColor,numPlayer);
		}
		
		/**
		 * pour s’assurer qu’on reste à l’interieur de la grille en Y.
		 */
		if (yP > 0) {  
			/**
			 * on examine la case d'en haut si elle existe si on est jamais passé et si elle a l'ancienne couleur
			 */
			analyseAdj(xP, yP - 1, oldColor, newColor,numPlayer);
		}
		if (yP+1 < ySize) {  
			/**
			 * on examine la case d'en haut si elle existe si on est jamais passé et si elle a l'ancienne couleur
			 */
			analyseAdj(xP, yP + 1, oldColor, newColor,numPlayer);
		}
	}
	
	private void analyseAdj(int x, int y,int oldColor, int newColor, int numPlayer) {
		if (grille[x][y].getJoueur() == null) {
			if (grille[x][y].getColor() == newColor) {
				grille[x][y].setColor(newColor); // dans ce cas on peut changer de couleur
				grille[x][y].setMajuscule(newColor);
				grille[x][y].setJoueur(joueurs[numPlayer-1]);		
				joueurs[numPlayer-1].incrNbCase();
				  	//puis on continue à explorer les cases adjacentes à gauche par récursivité
				adj(x, y, oldColor, newColor,numPlayer);
			}
		}
		
		else{
			System.out.println(grille[x][y].getJoueur()== joueurs[numPlayer-1]);
			System.out.println(grille[x][y].getColor()!=newColor);
			System.out.println(newColor);
			if(grille[x][y].getJoueur() == joueurs[numPlayer-1] && grille[x][y].getColor()!=newColor){
				grille[x][y].setColor(newColor);
				grille[x][y].setMajuscule(newColor);
				adj(x, y, oldColor, newColor,numPlayer);
			}
			
		}
		
		
	}
	
	
	
	/*private void analyseCompte(int x, int y, int numPlayer, int newColor, Joueur[] joueurs) {
		if ((grille[x][y].getDejaPasse())) {
			//puis on continue à explorer les cases adjacentes à gauche par récursivité
							
			
			//compte(numPlayer, x, y, newColor);
		}
		
	}
	
	private void compte(int numPlayer, int xP, int yP, int newColor) {
		
		if (xP > 0) {
			
			 //  on examine la case de gauche si elle existe si on est jamais passé et si elle a la nouvelle couleur
			 
			analyseCompte(xP - 1, yP, numPlayer, newColor, joueurs);
		}
		if (xP+1 < xSize) {
			
			 //  on examine la case de droite si elle existe si on est jamais passé et si elle a la nouvelle couleur
			 
			analyseCompte(xP + 1, yP, numPlayer, newColor, joueurs);
		}
		
		
		 // pour s’assurer qu’on reste à l’interieur de la grille en Y.
		 
		if (yP > 0) {  
			
			 // on examine la case du haut si elle existe si on est jamais passé et si elle a la nouvelle couleur
			 
			analyseCompte(xP, yP - 1, numPlayer, newColor, joueurs);
		}
		if (yP+1 < ySize) {  
			
			 // on examine la case du bas si elle existe si on est jamais passé et si elle a la nouvelle couleur
			 
			analyseCompte(xP, yP + 1, numPlayer, newColor, joueurs);
		}
	}*/
	
	

	//TODO change for a 5lines function that does the same
	public boolean alreadyPlayed(int nbPlayer, int player, int color) {
		boolean b = false;
		if (nbPlayer == 2) {
			if (player == 1) {
				if (grille[xSize-1][ySize-1].getColor() == color) {				//TODO Same than previous ones: might be inverted
					b = true;
				}
			}
			else if (player == 2) {
				if (grille[0][0].getColor() == color)  {
					b = true;		
				}	
			}
		}
		else if (nbPlayer == 3) {
			if (player == 1) {
				if ((grille[xSize-1][ySize-1].getColor()==color)||(grille[xSize-1][0].getColor()==color)) {		//TODO Same than previous ones: might be inverted
					b = true;
				}
			}
			else if (player == 2) {
				if ((grille[0][0].getColor()==color)||(grille[xSize-1][0].getColor()==color))  {	//TODO Same than previous ones: might be inverted
					b = true;		
				}
			}
			else if (player == 3) {
				if ((grille[0][0].getColor()==color)||(grille[xSize-1][ySize-1].getColor()==color))  {	//TODO Same than previous ones: might be inverted
					b = true;		
				}
			}
		}
		else if (nbPlayer == 4) {
			if (player == 1) {
				if ((grille[xSize-1][ySize-1].getColor()==color)||(grille[0][ySize-1].getColor()==color)||(grille[xSize-1][0].getColor()==color)) {		//TODO Same than previous ones: might be inverted
					b = true;
				}
			}
			else if (player == 2) {
				if ((grille[0][0].getColor()==color)||(grille[0][ySize-1].getColor()==color)||(grille[xSize-1][0].getColor()==color))  {
					b = true;		
				}
			}
			else if (player == 3) {
				if ((grille[0][0].getColor()==color)||(grille[xSize-1][ySize-1].getColor()==color)||(grille[0][ySize-1].getColor()==color))  {
					b = true;		
				}
			}
			else if (player == 4) {
				if ((grille[0][0].getColor()==color)||(grille[xSize-1][ySize-1].getColor()==color)||(grille[xSize-1][0].getColor()==color))  {
					b = true;		
				}
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