package p1;

import java.util.Random;

public class Deck {
	private Card cards[];
	private int numCards;
	
	public Deck() {
		//PRE:
		//POS: It creates an empty deck.
		this.cards=new Card[40];
		this.numCards=0;
	}
	
	public void startDeck() {
		//PRE: The deck must be already created.
		//POS: It adds the 40 cards to the deck.
		for(Stick s : Stick.values()) {
			for(int i=1;i<8;i++) {
				this.addCard(new Card(s,i));
			}
			for(int i=10;i<13;i++) {
				this.addCard(new Card(s,i));
			}
		}
	}
	
	public void shuffle() {
		//PRE: The deck must be already created and initialized.
		//POS: It chages the position of the cards.
		Random r=new Random();
		int i=r.nextInt(200);
		for(int c=0;c<i;c++) {
			this.exchange(r.nextInt(40),r.nextInt(40));
		}
	}
	
	public void exchange(int i1, int i2) {
		//PRE: The deck must be already created and initialized. i1 and i2 are positive integers.
		//POS: It changes the position of the cards located in the deck at i1 and i2.
		Card c=this.cards[i1];
		this.cards[i1]=this.cards[i2];
		this.cards[i2]=c;
	}
	
	public void addCard(Card c) {
		//PRE: The deck must be already created and c is a Card.
		//POS: It adds the card to the deck.
		this.cards[this.numCards]=c;
		this.numCards++;
	}
	
	public Card takeCard() {
		//PRE: The deck must be already created and not empty.
		//POS: It takes off the deck the last card.
		Card c=this.cards[this.numCards-1];
		this.numCards--;
		return c;
	}
	
	public int numCards() {
		//PRE: The deck must be already created.
		//POS: It returns the number of remaining cards of the deck.
		return this.numCards;
	}
		
	public void show() {
		//PRE: The deck must be already created.
		//POS: It shows the cards of the deck.
		for(int i=0;i<this.numCards();i++) {
			this.cards[i].show();
		}
	}
	public void deal(Player[] p,int players) {
		//PRE: The deck must be already created., j is an array of players and players is the number of players.
		//POS: Deal the cards between the players.
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
