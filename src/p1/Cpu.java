package p1;

public class Cpu extends Player{
	//La CPU sigue la estrategia "inteligente" de colocar la carta más alta que puede, ya que las tiene ordenadas de mayor a menor.
	public Card chooseCard(Table t) {
		//PRE: The player must be created as CPU. 
		//POS: It returns the first card that you can play on the Table m.
		if(this.canPlay(t)) {
			for(int i=0;i<this.numCards;i++) {
				if(t.canPlay(this.cards.get(i))) {
					this.numCards--;
					return this.cards.remove(i);
				}
			}
		}
		return null;
	}
}
