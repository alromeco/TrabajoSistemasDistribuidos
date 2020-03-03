package p1;

import java.util.Scanner;

public class Persona extends Jugador{
	public Carta elegirCarta(Mesa m) {
		this.mostrar();
		if(this.puedeJugar(m)) {
			System.out.println("Elige la carta que quieres jugar");
			Scanner teclado = new Scanner(System.in);
			int i = teclado.nextInt();
			while(this.puedeJugar(m)) {
				if(i<=this.numCartas && i>0 && m.puedeJugar(this.carta.get(i-1))) {
					this.numCartas--;
					return this.carta.remove(i-1);
				}else {
					System.out.println("No se puede jugar esa carta, elige otra");
					i = teclado.nextInt();
				}
			}
		}
		System.out.println("No puedes jugar ninguna de tus cartas");
		return null;
	}
}
