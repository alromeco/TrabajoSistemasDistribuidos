package p1;

import java.util.Scanner;

public class Persona extends Jugador{
	public Carta elegirCarta(Mesa m) {
		for(int i=0;i<this.carta.size();i++) {
			this.carta.get(i).mostrar();
		}
		System.out.println("Elige la carta que quieres jugar");
		Scanner teclado = new Scanner(System.in);
		int i = teclado.nextInt();
		while(this.puedeJugar(m)) {
			if(m.puedeJugar(this.carta.get(i))) {
				return this.carta.get(i);
			}else {
				System.out.println("No se puede jugar esa carta, elige otra");
				i = teclado.nextInt();
			}
		}
		return null;
	}
	
	public void recibirCarta(Carta c) {
		int i=0;
		if(c.getPalo()==Palo.BASTOS) {
			while(this.carta.get(i).getPalo()==Palo.BASTOS && !this.carta.contains(c)) {
				while(this.carta.get(i).getNumero()<c.getNumero()) {
					i++;
				}
				this.carta.add(i,c);
			}
		}
		if(c.getPalo()==Palo.ESPADAS) {
			i=0;
			while(this.carta.get(i).getPalo()==Palo.BASTOS && !this.carta.contains(c)) {
				while(this.carta.get(i).getNumero()<c.getNumero()) {
					i++;
				}
				this.carta.add(i,c);
			}
		}
		if(c.getPalo()==Palo.COPAS) {
			i=0;
			while(this.carta.get(i).getPalo()==Palo.BASTOS && !this.carta.contains(c)) {
				while(this.carta.get(i).getNumero()<c.getNumero()) {
					i++;
				}
				this.carta.add(i,c);
			}
		}else {
			i=0;
			while(this.carta.get(i).getPalo()==Palo.OROS && !this.carta.contains(c)) {
				while(this.carta.get(i).getNumero()<c.getNumero()) {
					i++;
				}
				this.carta.add(i,c);
			}
		}
	}
}
