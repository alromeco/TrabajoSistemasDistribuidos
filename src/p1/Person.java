package p1;

import java.util.Scanner;

public class Person extends Player{
	public Card chooseCard(Table t) {
		//PRE: El jugador debe estar creado como Persona. 
		//POS: Muestra las cartas al Jugador y, si puede jugar, pregunta qué Carta quiere jugar y la devuelve.
		//			En caso de que no pueda jugar, devuelve null.
		this.show();
		if(this.canPlay(t)) {
			System.out.println("Elige la carta que quieres jugar");
			Scanner teclado = new Scanner(System.in);
			int i = teclado.nextInt();
			while(this.canPlay(t)) {
				if(i<=this.numCards && i>0 && t.canPlay(this.cards.get(i-1))) {
					this.numCards--;
					return this.cards.remove(i-1);
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
