package p1;

public class Cpu extends Player{
	public Card chooseCard(Table m) {
		//PRE: The player must be created as CPU. 
		//POS: It returns the first card that you can play on the Table m.
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
