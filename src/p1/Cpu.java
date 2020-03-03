package p1;

public class Cpu extends Jugador{
	public Carta elegirCarta(Mesa m) {
		if(this.puedeJugar(m)) {
			for(int i=0;i<this.numCartas;i++) {
				if(m.puedeJugar(this.carta.get(i))) {
					this.numCartas--;
					return this.carta.remove(i);
				}
			}
		}
		return null;
	}
}
