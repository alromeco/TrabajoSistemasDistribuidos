package p1;

public class principal {
	public static void main(String[] args) {
		
		Carta c= new Carta(Palo.BASTOS,11);
		Carta c1= new Carta(Palo.OROS,12);
		Carta c2= new Carta(Palo.OROS,6);
		Carta c3= new Carta(Palo.OROS,4);
		Carta c0=new Carta(Palo.OROS,5);
		/*
		c.mostrar();
		System.out.println(c.getNumero());
		System.out.println(c.getPalo());
		c1.mostrar();
		System.out.println(c1.getNumero());
		System.out.println(c1.getPalo());
		c2.mostrar();
		System.out.println(c2.getNumero());
		System.out.println(c2.getPalo());
		c3.mostrar();
		System.out.println(c3.getNumero());
		System.out.println(c3.getPalo());
		*/
		/*
		Baraja b=new Baraja();
		b.iniciarBaraja();
		b.mostar();
		b.barajar();
		b.mostar();
		System.out.println(b.numCartas());
		b.sacarCarta().mostrar();
		System.out.println(b.numCartas());
		*/
		Mesa m=new Mesa();
		System.out.println(m.anadir(c0));
		System.out.println(m.anadir(c3));
		m.anadir(c2);
		m.mostrar();
		
	}
}
