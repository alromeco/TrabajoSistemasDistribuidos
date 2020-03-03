package p1;

import java.util.Random;

public class Baraja {
	private Carta cartas[];
	private int numCartas;
	
	public Baraja() {
		//PRE:
		//POS: Crea una Baraja vacía
		this.cartas=new Carta[40];
		this.numCartas=0;
	}
	
	public void iniciarBaraja() {
		//PRE: La baraja debe estar creada.
		//POS: Asigna las 40 cartas a la baraja ya creada.
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
		//PRE: La baraja debe estar creada e iniciada.
		//POS: Cambia de posición algunas cartas de la baraja.
		Random r=new Random();
		int i=r.nextInt(200);
		for(int c=0;c<i;c++) {
			this.intercambiar(r.nextInt(40),r.nextInt(40));
		}
	}
	
	public void intercambiar(int i1, int i2) {
		//PRE: La baraja debe estar iniciada y creada. i1 e i2 son dos enteros positivos.
		//POS: Intercambia de posicion las cartas de la baraja en posición i1 e i2.
		Carta c=this.cartas[i1];
		this.cartas[i1]=this.cartas[i2];
		this.cartas[i2]=c;
	}
	
	public void anadirCarta(Carta c) {
		//PRE: La Baraja debe estar creada y c es una Carta
		//POS: Añade la Carta c a la Baraja
		this.cartas[this.numCartas]=c;
		this.numCartas++;
	}
	
	public Carta sacarCarta() {
		//PRE: La Baraja debe estar creada y no estar vacía
		//POS: Saca de la baraja la última carta.
		Carta c=this.cartas[this.numCartas-1];
		this.numCartas--;
		return c;
	}
	
	public int numCartas() {
		//PRE: La Baraja debe estar creada.
		//POS: Devuelve el número de Cartas qeu quedan en la Baraja.
		return this.numCartas;
	}
		
	public void mostar() {
		//PRE: La Baraja debe estar creada.
		//POS: Muestra por pantalla las cartas que hay en la Baraja.
		for(int i=0;i<this.numCartas();i++) {
			this.cartas[i].mostrar();
		}
	}
	public void repartir(Jugador[] j,int jug) {
		//PRE: La Baraja debe estar creada, j es un vector de Jugadores y jug es el número de jugadores.
		//POS: Reparte las cartas de la Baraja entre los Jugadores.
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
