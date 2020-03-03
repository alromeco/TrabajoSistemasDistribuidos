package p1;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	protected List<Card> cards;
	protected int numCards;
	
	public Player() {
		//PRE: 
		//POS: Crea un jugador sin cartas.
		this.cards=new ArrayList<Card>();
		this.numCards=0;
	}
	
	public boolean canPlay(Table m) {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve cierto si puede jugar y falso en caso contrario.
		for(int i=0;i<this.numCards;i++) {
			if(m.canPlay(this.cards.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public int numCards() {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve el número de cartas que tiene el jugador.
		return this.numCards;
	}
	
	public abstract Card chooseCard(Table m);
	
	public void receiveCard(Card c) {
		//PRE: El jugador debe estar creado.
		//POS: Añade la Carta c a las cartas del Jugador.
		if(this.numCards()==0) {
			this.cards.add(c);
			this.numCards++;
		}else {
			int i=0;
			while(this.numCards()>i && this.cards.get(i).getNumber()>c.getNumber()) {
				i++;
			}
			this.cards.add(i,c);
			this.numCards++;
		}
	}
	
	public boolean ended() {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve cierto si el jugador se ha quedado sin cartas y falso en caso contrario
		return this.numCards()==0;
	}
	
	public void steal(Deck b) {
		//PRE: El jugador debe estar creado.
		//POS: Coge una carta de la Baraja b y la añade a su lista de cartas.
		this.receiveCard(b.takeCard());
	}
	
	public boolean hasCard(Card c) {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve cierto si el Jugador tiene la Carta c y falso en caso contrario.
		for(int i=0;i<this.numCards();i++) {
			if(this.cards.get(i).getStick().equals(c.getStick()) && this.cards.get(i).getNumber()==(c.getNumber())) {
				return true;
			}
		}
		return false;
	}
	
	public Card fiveGolds() {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve el cinco de oros, si no lo tiene, devuelve null.
		for(int i=0;i<this.numCards();i++) {
			if(this.cards.get(i).getStick().equals(Stick.OROS) && this.cards.get(i).getNumber()==5) {
				this.numCards--;
				return this.cards.remove(i);
			}
		}
		return null;
	}
	
	public void show() {
		//PRE: El jugador debe estar creado
		//POS: Muestra por pantalla las Cartas del Jugador.
		for(int i=0;i<this.cards.size();i++) {
			int x=i+1;
			System.out.println("Card number "+ x);
			this.cards.get(i).show();
		}
	}
}
