package isep.projet.sixcouleurs;

import java.util.Scanner;

public class ConsoleGame {
	private Main m;
	private Scanner s;
	
	public ConsoleGame(Main m){
		this.m=m;
		this.s=new Scanner(System.in);
	}
	
	public void init(){
		Joueur j;
		System.out.println();
		System.out.println("Saisissez le nombre de joueur: ");
		int nbPlayer = s.nextInt();
		
		Joueur[] joueurs = new Joueur[nbPlayer];
		for (int i = 0; i<nbPlayer; i++) {
			j = new Joueur(i+1);
			joueurs[i] = j;
		}
		this.m.setJoueurs(joueurs);

		System.out.println("Saisissez la longueur du damier: ");
		int xSize = s.nextInt();
		this.m.setxSize(xSize);

		System.out.println("Saisissez la hauteur du damier: ");
		int ySize=s.nextInt();
		this.m.setySize(ySize);
	}

	public void printTab(){
		for (int j=0;j<Game.getySize();j++){
			for (int i=0;i<Game.getxSize();i++){
				System.out.print((Game.getGrille()[i][j].getValue())+" ");
			}
			System.out.println();
		}
	}
	
	public void infosPlayer(Joueur joueur){
		System.out.println("nombre de Case pour le joueur " + joueur.getNumPlayer() + " est " + joueur.getNbCase());
	}
	
	public void win(int i){
		System.out.println("Le joueur " + i + " gagne la partie ");
		s.nextLine();
		System.out.println("Press enter to exit");
	}
	
	public int move(int i){
		System.out.println();
		System.out.println("Choisir une couleur pour le joueur " + i + " (R=0;B=1;V=2;J=3;O=4;I=5)");
		System.out.println();
		int color=s.nextInt();
		return color;
	}
	
	public void colorAlreadyTaken(){
		System.out.println("Cette couleur a déja été jouée !");
	}
}



















