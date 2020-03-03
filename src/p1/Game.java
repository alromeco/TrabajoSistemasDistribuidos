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
		//PRE: La Partida debe estar creada y jug debe ser un entero mayor que 1.
		//POS: Empieza la partida colocando el 5 de oros y, si no lo tiene nadie, otro 5.
		int i;
		int turn=0;
		boolean is=false;
		this.deck.deal(this.players, playrs);
		Card c=new Card(Stick.OROS,5);
		while(turn<playrs && !is) {
			is=players[turn].hasCard(c);
			if(!is) {
				turn++;
			}
		}
		if(is) {
			i=turn+1;
			System.out.println("Turno del jugador " + i +" , juega el 5 de oros:");
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
		//PRE: La partida debe estar empezada, turno es el turno del jugador al que le toca y jug el número de jugadores.
		//POS: Los Jugadores van colocando cartas en la mesa, siguiendo las normas del cinquillo, 
		//			hasta que alguien se queda sin cartas, que es el ganador.
		Card c=new Card();
		boolean fin=false;
		while(!fin) {
			int x=turn+1;
			System.out.println("Turno del jugador " + x +":");
			c=players[turn].chooseCard(this.table);
			if(c==null) {
				if(this.deck.numCards()!=0) {
					this.players[turn].steal(this.deck);
					System.out.println("Robas carta");
					System.out.println(" ");
				}
			}else {
				this.table.add(c);
				fin=this.players[turn].ended();
			}
			if(!fin)
				turn++;
			if(turn==playrs && !fin)
				turn=0;
			this.table.show();
			System.out.println("---------------------");
		}
		int x=turn+1;
		System.out.println("Gana el jugador "+ x);
	}
}
