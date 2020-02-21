package p1;

import java.util.List;

public abstract class Jugador {
	protected List<Carta> carta;
	
	public Jugador() {
		this.carta=null;
	}
	
	public boolean puedeJugar(Mesa m) {
		
	}
	
	public int numCartas() {
		return this.carta.size();
	}
	
	public abstract Carta elegirCarta(Mesa m);
	
	public abstract void recicbirCarta(Carta c);
	
	public boolean Finalizado() {
		return this.numCartas()==0;
	}
}
