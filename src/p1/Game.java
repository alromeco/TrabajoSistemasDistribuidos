package p1;

import java.util.Random;

public class Game {
	private Table table;
	private Player[] players;
	private Deck deck;
	
	public Game(Table t, int pers,int cpu, Deck d) {
		//PRE: La mesa m y la Baraja b deben estar creadas y b no debe estar vacía. pers y cpu deben ser enteros positivos o 0
		//POS: Crea una Partida con Mesa m, pers personas reales, cpu jugadores de la cpu y baraja b.
		this.table=t;
		this.players=new Player[pers+cpu];
		this.deck=d;
		for(int i=0;i<pers;i++) {
			Player j=new Person();
			this.players[i]=j;
		}
		for(int i=pers;i<pers+cpu;i++) {
			Player j=new Cpu();
			this.players[i]=j;
		}
	}
	
	public void startGame(int playrs) {
		//PRE: The Game must be crated and playrs an integer higher than 1.
		//POS: The Game starts putting the 5 of golds. If anyone has it, then any 5 starts.
		int i;
		int turn=0;
		boolean is=false;
		this.deck.deal(this.players, playrs);
		Card c=new Card(Stick.GOLDS,5);
		while(turn<playrs && !is) {
			is=players[turn].hasCard(c);
			if(!is) {
				turn++;
			}
		}
		if(is) {
			i=turn+1;
			System.out.println("Player "+i+" turn "+" , You are starting with the 5 of golds:");
			this.table.add(players[turn].fiveGolds());
			this.table.show();
			System.out.println("---------------------");
			if(turn==playrs-1) {
				turn=0;
			}else {
				turn++;
			}
		}else {
			Random r=new Random();
			int a=r.nextInt(playrs-1);
			turn=a;
		}
		this.continueGame(turn,playrs);
	}
	
	public void continueGame(int turn,int playrs) {
		//PRE: The game must be started, turn is the turn of the player that plays now and playrs the number of players.
		//POS: Players put cards on the deck, following the rules of cinquillo, 
		//			until someone runs out of cards, who is the winner.
		Card c=new Card();
		boolean end=false;
		while(!end) {
			int x=turn+1;
			System.out.println("Player "+x+" turn:");
			c=players[turn].chooseCard(this.table);
			if(c==null) {
				if(this.deck.numCards()!=0) {
					this.players[turn].steal(this.deck);
					System.out.println("You take a card of the deck.");
					System.out.println(" ");
				}
			}else {
				this.table.add(c);
				end=this.players[turn].ended();
			}
			if(!end)
				turn++;
			if(turn==playrs && !end)
				turn=0;
			this.table.show();
			System.out.println("---------------------");
		}
		int x=turn+1;
		System.out.println("Player "+ x + " wins");
	}
}
