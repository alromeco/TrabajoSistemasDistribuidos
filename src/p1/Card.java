package p1;

public class Card {
	private Stick stick;
	private int number;
	
	public Card() {
		//PRE:
		//POS: Inicia una carta vacía.
		this.stick=null;
		this.number=0;
	}
	
	public Card(Stick p,int n) {
		//PRE: P es un Palo y n es un entero entre 1-7(incluidos) y 10, 11 o 12
		//POS: Inicia una carta con Palo p y número n.
		this.stick=p;
		this.number=n;
	}
	
	public Stick getStick() {
		//PRE: La Carta debe estar creada.
		//POS: Devuelve el Palo de la Carta.
		return this.stick;
	}
	
	public int getNumber() {
		//PRE: La Carta debe estar creada.
		//POS: Devuelve el número de la Carta.
		return this.number;
	}
	
	public void show() {
		//PRE: La Carta debe estar creada.
		//POS: Muestra por pantalla la Carta.
		System.out.println("[ "+this.number+" of "+this.stick+" ]");	
	}
	
}
