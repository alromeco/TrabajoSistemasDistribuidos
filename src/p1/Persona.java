package p1;

import java.util.Scanner;

public class Persona extends Jugador{
	public Carta elegirCarta(Mesa m) {
		for(int i=0;i<this.carta.size();i++) {
			System.out.println("Carta numero "+i);
			this.carta.get(i).mostrar();
		}
		if(this.puedeJugar(m)) {
			System.out.println("Elige la carta que quieres jugar");
			Scanner teclado = new Scanner(System.in);
			int i = teclado.nextInt();
			while(this.puedeJugar(m)) {
				if(i<this.numCartas && i>=0 && m.puedeJugar(this.carta.get(i))) {
					this.numCartas--;
					return this.carta.remove(i);
				}else {
					System.out.println("No se puede jugar esa carta, elige otra");
					i = teclado.nextInt();
				}
			}
		}
		System.out.println("No puedes jugar ninguna de tus cartas");
		return null;
	}
	
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
}
