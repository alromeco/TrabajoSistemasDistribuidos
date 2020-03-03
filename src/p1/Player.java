package p1;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	protected List<Card> cards;
	protected int numCards;
	
	public Player() {
		//PRE: 
		//POS: It creates a player without cards.
		this.cards=new ArrayList<Card>();
		this.numCards=0;
	}
	
	public boolean canPlay(Table t) {
		//PRE: The player must be created.
		//POS: Returns true if the player can put a card, false in other case.
		for(int i=0;i<this.numCards;i++) {
			if(t.canPlay(this.cards.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public int numCards() {
		//PRE: The player must be created.
		//POS: Returns the number of cards of the player.
		return this.numCards;
	}
	
	public abstract Card chooseCard(Table t);
	
	public void receiveCard(Card c) {
		//PRE: Player must be created.
		//POS: Adds the Card c to the list of cards of the Player.
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
		//PRE: Player must be created.
		//POS: Returns true if the player has no more cards, false in other case.
		return this.numCards()==0;
	}
	
	public void steal(Deck d) {
		//PRE: Player must be created.
		//POS: Player takes a Card from the Deck d and he adds it to its cards.
		this.receiveCard(d.takeCard());
	}
	
	public boolean hasCard(Card c) {
		//PRE: Player must be created.
		//POS: Returns true if the Player has the Card c, false in other case.
		for(int i=0;i<this.numCards();i++) {
			if(this.cards.get(i).getStick().equals(c.getStick()) && this.cards.get(i).getNumber()==(c.getNumber())) {
				return true;
			}
		}
		return false;
	}
	
	public Card fiveGolds() {
		//PRE: Player must be created.
		//POS: Returns the 5 of golds, or null if the Player doesn't have it.
		for(int i=0;i<this.numCards();i++) {
			if(this.cards.get(i).getStick().equals(Stick.GOLDS) && this.cards.get(i).getNumber()==5) {
				this.numCards--;
				return this.cards.remove(i);
			}
		}
		return null;
	}
	
	public void show() {
		//PRE: Player must be created.
		//POS: Shows the list of cards of the Player.
		for(int i=0;i<this.cards.size();i++) {
			int x=i+1;
			System.out.println("Card number "+ x);
			this.cards.get(i).show();
		}
	}
}
