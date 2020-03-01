package p1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Jugador {
	protected List<Carta> carta;
	protected int numCartas;
	
	public Jugador() {
		this.carta=new ArrayList<Carta>();
		this.numCartas=0;
	}
	
	public boolean puedeJugar(Mesa m) {
		for(int i=0;i<this.numCartas;i++) {
			if(m.puedeJugar(this.carta.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public int numCartas() {
		return this.numCartas;
	}
	
	public abstract Carta elegirCarta(Mesa m);
	
	public abstract void recibirCarta(Carta c);
	
	public boolean Finalizado() {
		return this.numCartas()==0;
	}
	
	public void robar(Baraja b) {
		this.recibirCarta(b.sacarCarta());
		this.numCartas++;
	}
	public boolean tieneCarta(Carta c) {
		return this.carta.contains(c);
	}
}
