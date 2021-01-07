package p1;

public class Card {
	private Stick stick;
	private int number;
	
	public Card() {
		//PRE:
		//POS: It creates an empty card.
		this.stick=null;
		this.number=0;
	}
	
	public Card(Stick s,int n) {
		//PRE: P is a stick and n is an integer between 1-7(included) and 10, 11 or 12
		//POS: It creates a card with stick p and number n.
		this.stick=s;
		this.number=n;
	}
	
	public Stick getStick() {
		//PRE: The card must be already created.
		//POS: It returns the stick of the card.
		return this.stick;
	}
	
	public int getNumber() {
		//PRE: The card must be already created.
		//POS: It returns the number of the card.
		return this.number;
	}
	
	public String show() {
		//PRE: The card must be already created.
		//POS: It shows the card.
		return("[ "+this.number+" of "+this.stick+" ]");	
	}
	
}
