package p1;

public class Carta {
	private Palo palo;
	private int numero;
	public Carta() {
		//PRE: 
		//POS:the card has been initialized
		this.palo=null;
		this.numero=0;
	}
	public Carta(Palo p,int n) {
		//PRE: 
		//POS:the card has been initialized
		this.palo=p;
		this.numero=n;
	}
	public Palo getPalo() {
		//PRE: the card is already initialized
		//POS: it returns the suit of the card
		return this.palo;
	}
	public int getNumero() {
		//PRE: the card is already initialized
		//POS: it returns the number of the card
		return this.numero;
	}
	public void mostrar() {
		//PRE: the card is already initialized
		//POS: it shows the card
		System.out.println("[ "+this.numero+" de "+this.palo+" ]");	
	}
	
}
