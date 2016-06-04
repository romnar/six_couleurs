package isep.projet.sixcouleurs;

public class PlayingEntities {

	private Player[] players;
	private Player activePlayer;
	
	public void init(int playerNumber,Game game,Tokens tokens,int cpuNumber){
		
		boolean isCpu;
		
		players=new Player[playerNumber];
		
		for (int i=playerNumber;i>0;i--){
			
			int random=(int)(Math.random()*playerNumber)+1;
			if (random<=cpuNumber||cpuNumber==i) {
				isCpu=true;
				cpuNumber--;
			}
			else isCpu=false;
			
			this.players[i-1]=new Player();
			this.players[i-1].init(i-1,isCpu);
			game.init(players[i-1],tokens);
		}
		
		this.activePlayer=players[0];
		
	}
	
	public void nextPlayer(){
		
		this.activePlayer=players[((this.activePlayer.getNumPlayer())%players.length)];
		
	}
	
	public Player getBestAfterActive(){
		
		int bestAfterActive=(this.activePlayer.getNumPlayer())%this.players.length;
		for (int i=0;i<players.length;i++){
			if (players[i].getNbCase()>players[bestAfterActive].getNbCase() && players[i]!=this.activePlayer) bestAfterActive=i;
		}
		return this.players[bestAfterActive];
		
	}
	
	public Player[] getWinners(){
		
		int bestCaseNb=0;
		int winnerNumber=1;
		
		for (int i=0;i<players.length;i++){
			if (players[i].getNbCase()==bestCaseNb) winnerNumber++;
			if (players[i].getNbCase()>bestCaseNb) {
				bestCaseNb=players[i].getNbCase();
				winnerNumber=1;
			}
		}
		
		Player[] winners=new Player[winnerNumber];
		int count=0;
		for (int i=0;i<players.length;i++){
			if (players[i].getNbCase()==bestCaseNb){
				winners[count]=players[i];
				count++;
			}
		}
		
		return winners;
		
	}

	public boolean hasGameEnd(int totalCase){
		
		int percentPossessed=0;
		
		for (Player p:this.players)
			percentPossessed+=p.getNbCase();
		
		if (percentPossessed==totalCase)
			return true;
		return false;
		
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public Player[] getPlayers() {
		return players;
	}
	
}
