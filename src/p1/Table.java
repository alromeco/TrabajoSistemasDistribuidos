package p1;

import java.util.Deque;
import java.util.LinkedList;

public class Table {
	private Deque<Card> Bastos;
	private Deque<Card> Espadas;
	private Deque<Card> Copas;
	private Deque<Card> Oros;
	
	public Table() {
		//PRE:
		//POS: It creates a table.
		this.Bastos=new LinkedList<Card>();
		this.Copas=new LinkedList<Card>();
		this.Espadas=new LinkedList<Card>();
		this.Oros=new LinkedList<Card>();
	}
	
	public boolean add(Card c) {
		//PRE: The table must be already created.
		//POS: It adds the card c to the table.
		Deque<Card> aux;
		switch(c.getStick()) {
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
				aux=new LinkedList<Card>();
				break;
		}
		if(c.getNumber()==5) {
			aux.add(c);
			return true;
		}else {
			if(c.getNumber()<5) {
				if(c.getNumber()+1==aux.getFirst().getNumber()) {
					aux.addFirst(c);
					return true;
				}
				return false;
			}else {
				if(c.getNumber()-1==aux.getLast().getNumber()) {
					aux.addLast(c);
					return true;
				}
				if(c.getNumber()==10 && aux.getLast().getNumber()==7) {
					aux.addLast(c);
					return true;
				}
				return false;
			}
		}
	}
	
	public boolean canPlay(Card c) {
		//PRE: The table must be already created.
		//POS: It returns true if you can play the card c in this table.
		Deque<Card> aux;
		switch(c.getStick()) {
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
		if(c.getNumber()==5) {
			return true;
		}else {
			if(c.getNumber()<5) {
				if(aux.size()==0) {
					return false;
				}
				if(c.getNumber()+1==aux.getFirst().getNumber()) {
					return true;
				}
				return false;
			}else {
				if(aux.size()==0) {
					return false;
				}
				if(c.getNumber()-1==aux.getLast().getNumber()) {
					return true;
				}
				if(c.getNumber()==10 && aux.getLast().getNumber()==7) {
					return true;
				}
				return false;
			}
		}
	}
	
	public void show() {
		//PRE: The table must be already created.
		//POS: It shows the cards on the table, ordered by sticks.
		System.out.println("Table:");
		for(Card c: this.Bastos) {
			c.show();
		}
		for(Card c:this.Espadas) {
			c.show();
		}
		for(Card c:this.Oros) {
			c.show();
		}
		for(Card c:this.Copas) {
			c.show();
		}
	}
}
