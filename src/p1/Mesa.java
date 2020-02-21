package p1;

import java.util.Deque;

public class Mesa {
	private Deque<Carta> Bastos;
	private Deque<Carta> Espadas;
	private Deque<Carta> Copas;
	private Deque<Carta> Oros;
	public Mesa() {
		this.Bastos=null;
		this.Copas=null;
		this.Espadas=null;
		this.Oros=null;
	}
	public boolean anadir(Carta c) {
		Deque<Carta> aux;
		switch(c.getPalo()) {
			case BASTOS :
				aux=this.Bastos;
			case ESPADAS :
				aux=this.Espadas;
			case OROS :
				aux=this.Oros;
			case COPAS :
				aux=this.Copas;
			default:
				aux=null;
		}
		if(c.getNumero()==5) {
			aux.add(c);
			return true;
		}else {
			if(c.getNumero()<5) {
				if(c.getNumero()+1==aux.getFirst().getNumero()) {
					aux.addFirst(c);
					return true;
				}
				return false;
			}else {
				if(c.getNumero()-1==aux.getLast().getNumero()) {
					aux.addLast(c);
					return true;
				}
				if(c.getNumero()==10 && aux.getLast().getNumero()==7) {
					aux.addLast(c);
					return true;
				}
				return false;
			}
		}
	}
	public boolean puedeJugar(Carta c) {
		Deque<Carta> aux;
		switch(c.getPalo()) {
			case BASTOS :
				aux=this.Bastos;
			case ESPADAS :
				aux=this.Espadas;
			case OROS :
				aux=this.Oros;
			default:
				aux=this.Copas;
		}
		if(c.getNumero()==5) {
			return true;
		}else {
			if(c.getNumero()<5) {
				if(c.getNumero()+1==aux.getFirst().getNumero()) {
					return true;
				}
				return false;
			}else {
				if(c.getNumero()-1==aux.getLast().getNumero()) {
					return true;
				}
				if(c.getNumero()==10 && aux.getLast().getNumero()==7) {
					return true;
				}
				return false;
			}
		}
	}
	public void mostrar() {
		for(Carta c: this.Bastos) {
			c.mostrar();
		}
		for(Carta c: this.Espadas) {
			c.mostrar();
		}
		for(Carta c: this.Oros) {
			c.mostrar();
		}
		for(Carta c: this.Copas) {
			c.mostrar();
		}
	}
}
