package p1;

import java.util.Random;

public class Baraja {
	private Carta cartas[];
	private int numCartas;
	
	public Baraja() {
		this.cartas=new Carta[40];
		this.numCartas=0;
	}
	
	public void iniciarBaraja() {
		for(Palo p : Palo.values()) {
			for(int i=1;i<8;i++) {
				this.anadirCarta(new Carta(p,i));
			}
			for(int i=10;i<13;i++) {
				this.anadirCarta(new Carta(p,i));
			}
		}
	}
	
	public void barajar() {
		Random r=new Random();
		int i=r.nextInt(40);
		for(int c=0;c<i;c++) {
			this.intercambiar(r.nextInt(40),r.nextInt(40));
		}
	}
	
	public void intercambiar(int i1, int i2) {
		Carta c=this.cartas[i1];
		this.cartas[i1]=this.cartas[i2];
		this.cartas[i2]=c;
	}
	
	public void anadirCarta(Carta c) {
		this.cartas[this.numCartas]=c;
		this.numCartas++;
	}
	
	public Carta sacarCarta() {
		Carta c=this.cartas[this.numCartas-1];
		this.numCartas--;
		return c;
	}
	
	public int numCartas() {
		return this.numCartas;
	}
		
	public void mostar() {
		for(int i=0;i<this.numCartas();i++) {
			this.cartas[i].mostrar();
		}
	}
	public void repartir(Jugador[] j,int jug) {
		this.barajar();
		if(jug<=4) {
			for(int i=0;i<jug;i++) {
				for(int n=0;n<10;n++) {
					j[i].recibirCarta(this.sacarCarta());
				}
			}
		}else {
			while(this.numCartas!=0) {
				int x=0;
				while(x<jug && this.numCartas!=0) {
					j[x].recibirCarta(this.sacarCarta());
					x++;
				}
			}
		}
	}
	
}
