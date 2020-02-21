package p1;

public class Cpu extends Jugador{
	public Carta elegirCarta(Mesa m) {
		if(this.puedeJugar(m)) {
			for(int i=0;i<this.carta.size();i++) {
				if(m.puedeJugar(this.carta.get(i))) {
					return this.carta.get(i);
				}
			}
		}
		return null;
	}
	
	public void recibirCarta(Carta c) {
		int i=0;
		while(this.carta.get(i).getNumero()>c.getNumero()) {
			i++;
		}
		this.carta.add(i,c);
	}
}
