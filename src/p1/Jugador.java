package p1;

import java.util.List;

public abstract class Jugador {
	protected List<Carta> carta;
	
	public Jugador() {
		this.carta=null;
	}
	
	public boolean puedeJugar(Mesa m) {
		for(int i=0;i<this.carta.size();i++) {
			if(m.puedeJugar(this.carta.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public int numCartas() {
		return this.carta.size();
	}
	
	public abstract Carta elegirCarta(Mesa m);
	
	public abstract void recibirCarta(Carta c);
	
	public boolean Finalizado() {
		return this.numCartas()==0;
	}
	
	public void robar(Baraja b) {
		this.recibirCarta(b.sacarCarta());
	}
}
