package p1;

public class Carta {
	private Palo palo;
	private int numero;
	
	public Carta() {
		//PRE:
		//POS: Inicia una carta vacía.
		this.palo=null;
		this.numero=0;
	}
	
	public Carta(Palo p,int n) {
		//PRE: P es un Palo y n es un entero entre 1-7(incluidos) y 10, 11 o 12
		//POS: Inicia una carta con Palo p y número n.
		this.palo=p;
		this.numero=n;
	}
	
	public Palo getPalo() {
		//PRE: La Carta debe estar creada.
		//POS: Devuelve el Palo de la Carta.
		return this.palo;
	}
	
	public int getNumero() {
		//PRE: La Carta debe estar creada.
		//POS: Devuelve el número de la Carta.
		return this.numero;
	}
	
	public void mostrar() {
		//PRE: La Carta debe estar creada.
		//POS: Muestra por pantalla la Carta.
		System.out.println("[ "+this.numero+" de "+this.palo+" ]");	
	}
	
}
