package p1;

public class principal {
	public static void main(String[] args) {
		Carta c= new Carta(Palo.BASTOS,10);
		c.mostrar();
		baraja b=new baraja();
		b.inicuarbaraja();
	}
}
