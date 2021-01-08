package p1;

import java.util.Deque;
import java.util.LinkedList;

public class Table {
	private Deque<Card> clubs;
	private Deque<Card> swords;
	private Deque<Card> cups;
	private Deque<Card> golds;
	
	public Table() {
		//PRE:
		//POS: It creates a table.
		this.clubs=new LinkedList<Card>();
		this.cups=new LinkedList<Card>();
		this.swords=new LinkedList<Card>();
		this.golds=new LinkedList<Card>();
	}
	
	public boolean add(Card c) {
		//PRE: The table must be already created.
		//POS: It adds the card c to the table.
		Deque<Card> aux;
		switch(c.getStick()) {
			case CLUBS :
				aux=this.clubs;
				break;
			case SWORDS :
				aux=this.swords;
				break;
			case GOLDS :
				aux=this.golds;
				break;
			case CUPS :
				aux=this.cups;
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
			case CLUBS :
				aux=this.clubs;
				break;
			case SWORDS :
				aux=this.swords;
				break;
			case GOLDS :
				aux=this.golds;
				break;
			default:
				aux=this.cups;
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
	
	public String show() {
		//PRE: The table must be already created.
		//POS: It shows the cards on the table, ordered by sticks.
		String s;
		s="Table:\r\n";
		for(Card c: this.clubs) {
			s=s+c.show()+"\r\n";
		}
		s=s+"\r\n";
		for(Card c:this.swords) {
			s=s+c.show()+"\r\n";
		}
		s=s+"\r\n";
		for(Card c:this.golds) {
			s=s+c.show()+"\r\n";
		}
		s=s+"\r\n";
		for(Card c:this.cups) {
			s=s+c.show()+"\r\n";
		}
		s=s+"\r\n";
		return s;
	}
}
