package p1;

public class principal {
	public static void main(String[] args) {
		Carta c= new Carta(Palo.BASTOS,10);
		c.mostrar();
		Baraja b=new Baraja();
		b.iniciarBaraja();
		b.mostar();
	}
}
