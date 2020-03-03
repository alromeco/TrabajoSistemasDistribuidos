package p1;

import java.util.Random;

public class Deck {
	private Card cards[];
	private int numCards;
	
	public Deck() {
		//PRE:
		//POS: Crea una Baraja vacía
		this.cards=new Card[40];
		this.numCards=0;
	}
	
	public void startDeck() {
		//PRE: La baraja debe estar creada.
		//POS: Asigna las 40 cartas a la baraja ya creada.
		for(Stick p : Stick.values()) {
			for(int i=1;i<8;i++) {
				this.addCard(new Card(p,i));
			}
			for(int i=10;i<13;i++) {
				this.addCard(new Card(p,i));
			}
		}
	}
	
	public void shuffle() {
		//PRE: La baraja debe estar creada e iniciada.
		//POS: Cambia de posición algunas cartas de la baraja.
		Random r=new Random();
		int i=r.nextInt(200);
		for(int c=0;c<i;c++) {
			this.exchange(r.nextInt(40),r.nextInt(40));
		}
	}
	
	public void exchange(int i1, int i2) {
		//PRE: La baraja debe estar iniciada y creada. i1 e i2 son dos enteros positivos.
		//POS: Intercambia de posicion las cartas de la baraja en posición i1 e i2.
		Card c=this.cards[i1];
		this.cards[i1]=this.cards[i2];
		this.cards[i2]=c;
	}
	
	public void addCard(Card c) {
		//PRE: La Baraja debe estar creada y c es una Carta
		//POS: Añade la Carta c a la Baraja
		this.cards[this.numCards]=c;
		this.numCards++;
	}
	
	public Card takeCard() {
		//PRE: La Baraja debe estar creada y no estar vacía
		//POS: Saca de la baraja la última carta.
		Card c=this.cards[this.numCards-1];
		this.numCards--;
		return c;
	}
	
	public int numCards() {
		//PRE: La Baraja debe estar creada.
		//POS: Devuelve el número de Cartas qeu quedan en la Baraja.
		return this.numCards;
	}
		
	public void show() {
		//PRE: La Baraja debe estar creada.
		//POS: Muestra por pantalla las cartas que hay en la Baraja.
		for(int i=0;i<this.numCards();i++) {
			this.cards[i].show();
		}
	}
	public void deal(Player[] p,int players) {
		//PRE: La Baraja debe estar creada, j es un vector de Jugadores y jug es el número de jugadores.
		//POS: Reparte las cartas de la Baraja entre los Jugadores.
		this.shuffle();
		if(players<=4) {
			for(int i=0;i<players;i++) {
				for(int n=0;n<10;n++) {
					p[i].receiveCard(this.takeCard());
				}
			}
		}else {
			while(this.numCards!=0) {
				int x=0;
				while(x<players && this.numCards!=0) {
					p[x].receiveCard(this.takeCard());
					x++;
				}
			}
		}
	}
	
}
