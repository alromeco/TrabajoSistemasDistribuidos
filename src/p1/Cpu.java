package p1;

public class Cpu extends Player{
	public Card chooseCard(Table m) {
		//PRE: El jugador debe estar creado como CPU. 
		//POS: Devuelve la primera Carta que puede jugar en la Mesa m.
		if(this.canPlay(m)) {
			for(int i=0;i<this.numCards;i++) {
				if(m.canPlay(this.cards.get(i))) {
					this.numCards--;
					return this.cards.remove(i);
				}
			}
		}
		return null;
	}
}
