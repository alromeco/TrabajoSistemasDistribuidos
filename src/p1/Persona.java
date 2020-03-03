package p1;

import java.util.Scanner;

public class Persona extends Jugador{
	public Carta elegirCarta(Mesa m) {
		//PRE: El jugador debe estar creado como Persona. 
		//POS: Muestra las cartas al Jugador y, si puede jugar, pregunta qué Carta quiere jugar y la devuelve.
		//			En caso de que no pueda jugar, devuelve null.
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
