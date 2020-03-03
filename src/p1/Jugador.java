package p1;

import java.util.ArrayList;
import java.util.List;

public abstract class Jugador {
	protected List<Carta> carta;
	protected int numCartas;
	
	public Jugador() {
		//PRE: 
		//POS: Crea un jugador sin cartas.
		this.carta=new ArrayList<Carta>();
		this.numCartas=0;
	}
	
	public boolean puedeJugar(Mesa m) {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve cierto si puede jugar y falso en caso contrario.
		for(int i=0;i<this.numCartas;i++) {
			if(m.puedeJugar(this.carta.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public int numCartas() {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve el número de cartas que tiene el jugador.
		return this.numCartas;
	}
	
	public abstract Carta elegirCarta(Mesa m);
	
	public void recibirCarta(Carta c) {
		//PRE: El jugador debe estar creado.
		//POS: Añade la Carta c a las cartas del Jugador.
		if(this.numCartas()==0) {
			this.carta.add(c);
			this.numCartas++;
		}else {
			int i=0;
			while(this.numCartas()>i && this.carta.get(i).getNumero()>c.getNumero()) {
				i++;
			}
			this.carta.add(i,c);
			this.numCartas++;
		}
	}
	
	public boolean Finalizado() {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve cierto si el jugador se ha quedado sin cartas y falso en caso contrario
		return this.numCartas()==0;
	}
	
	public void robar(Baraja b) {
		//PRE: El jugador debe estar creado.
		//POS: Coge una carta de la Baraja b y la añade a su lista de cartas.
		this.recibirCarta(b.sacarCarta());
	}
	
	public boolean tieneCarta(Carta c) {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve cierto si el Jugador tiene la Carta c y falso en caso contrario.
		for(int i=0;i<this.numCartas();i++) {
			if(this.carta.get(i).getPalo().equals(c.getPalo()) && this.carta.get(i).getNumero()==(c.getNumero())) {
				return true;
			}
		}
		return false;
	}
	
	public Carta cincoOros() {
		//PRE: El jugador debe estar creado.
		//POS: Devuelve el cinco de oros, si no lo tiene, devuelve null.
		for(int i=0;i<this.numCartas();i++) {
			if(this.carta.get(i).getPalo().equals(Palo.OROS) && this.carta.get(i).getNumero()==5) {
				this.numCartas--;
				return this.carta.remove(i);
			}
		}
		return null;
	}
	
	public void mostrar() {
		//PRE: El jugador debe estar creado
		//POS: Muestra por pantalla las Cartas del Jugador.
		for(int i=0;i<this.carta.size();i++) {
			int x=i+1;
			System.out.println("Carta número "+ x);
			this.carta.get(i).mostrar();
		}
	}
}
