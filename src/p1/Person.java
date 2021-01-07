package p1;

import java.util.Scanner;

public class Person extends Player {
	public Card chooseCard(Table t) {
		// PRE: The player must be already created as Person.
		// POS: It shows the player's cards and, if he can play, asks what card he wants
		// to play with and returns it, removing it from his cards.
		// In case that he can not play, it returns null.
		this.show();
		if (this.canPlay(t)) {
			System.out.println("Choose the card that you want to play");
			Scanner teclado = new Scanner(System.in);
			int i = teclado.nextInt();
			while (this.canPlay(t)) {
				if (i <= this.numCards && i > 0 && t.canPlay(this.cards.get(i - 1))) {
					this.numCards--;
					return this.cards.remove(i - 1);
				} else {
					System.out.println("You can't play that card, choose another");
					i = teclado.nextInt();
				}
			}
		}
		System.out.println("You can't play any of your cards");
		return null;
	}

}
