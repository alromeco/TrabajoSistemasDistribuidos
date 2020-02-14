package p1;

import java.util.Random;

public class Baraja {
	private Carta cartas[];
	private int numCartas;
	
	public Baraja() {
		this.cartas=new Carta[40];
		this.numCartas=40;
	}
	
	public void iniciarBaraja() {
		
	}
	
	public barajar(Baraja b) {
		Random r=new Random();
		int i=r.nextInt(40);
		int c=0;
		while(c<i) {
			intercambiar(r.nextInt(40),r.nextInt(40));
		}
	}
	
	public void intercambiar(int i1, int i2) {
		Carta c=this.cartas[i1];
		this.cartas[i1]=this.cartas[i2];
		this.cartas[i2]=c;
	}
}
