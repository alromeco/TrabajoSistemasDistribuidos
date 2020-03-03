package p1;

import java.util.Deque;
import java.util.LinkedList;

public class Mesa {
	private Deque<Carta> Bastos;
	private Deque<Carta> Espadas;
	private Deque<Carta> Copas;
	private Deque<Carta> Oros;
	public Mesa() {
		this.Bastos=new LinkedList<Carta>();
		this.Copas=new LinkedList<Carta>();
		this.Espadas=new LinkedList<Carta>();
		this.Oros=new LinkedList<Carta>();
	}
	public boolean anadir(Carta c) {
		Deque<Carta> aux;
		switch(c.getPalo()) {
			case BASTOS :
				aux=this.Bastos;
				break;
			case ESPADAS :
				aux=this.Espadas;
				break;
			case OROS :
				aux=this.Oros;
				break;
			case COPAS :
				aux=this.Copas;
				break;
			default:
				aux=new LinkedList<Carta>();
				break;
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
				break;
			case ESPADAS :
				aux=this.Espadas;
				break;
			case OROS :
				aux=this.Oros;
				break;
			default:
				aux=this.Copas;
				break;
		}
		if(c.getNumero()==5) {
			return true;
		}else {
			if(c.getNumero()<5) {
				if(aux.size()==0) {
					return false;
				}
				if(c.getNumero()+1==aux.getFirst().getNumero()) {
					return true;
				}
				return false;
			}else {
				if(aux.size()==0) {
					return false;
				}
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
		System.out.println("Mesa:");
		for(Carta c: this.Bastos) {
			c.mostrar();
		}
		for(Carta c:this.Espadas) {
			c.mostrar();
		}
		for(Carta c:this.Oros) {
			c.mostrar();
		}
		for(Carta c:this.Copas) {
			c.mostrar();
		}
	}
}
