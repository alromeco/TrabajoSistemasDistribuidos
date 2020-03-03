package p1;

import java.util.ArrayList;
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
	
	public void recibirCarta(Carta c) {
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
		return this.numCartas()==0;
	}
	
	public void robar(Baraja b) {
		this.recibirCarta(b.sacarCarta());
	}
	public boolean tieneCarta(Carta c) {
		for(int i=0;i<this.numCartas();i++) {
			if(this.carta.get(i).getPalo().equals(c.getPalo()) && this.carta.get(i).getNumero()==(c.getNumero())) {
				return true;
			}
		}
		return false;
	}
	public Carta cincoOros() {
		for(int i=0;i<this.numCartas();i++) {
			if(this.carta.get(i).getPalo().equals(Palo.OROS) && this.carta.get(i).getNumero()==5) {
				this.numCartas--;
				return this.carta.remove(i);
			}
		}
		return null;
	}
	public void mostrar() {
		for(int i=0;i<this.carta.size();i++) {
			int x=i+1;
			System.out.println("Carta número "+ x);
			this.carta.get(i).mostrar();
		}
	}
}
