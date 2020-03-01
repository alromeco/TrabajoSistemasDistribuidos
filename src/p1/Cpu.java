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
