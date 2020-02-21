package p1;

public class Persona extends Jugador{
	public Carta elegirCarta(Mesa m) {
		
	}
	
	public void recibirCarta(Carta c) {
		int i=0;
		if(c.getPalo()==Palo.BASTOS) {
			while(this.carta.get(i).getPalo()==Palo.BASTOS && !this.carta.contains(c)) {
				while(this.carta.get(i).getNumero()<c.getNumero()) {
					i++;
				}
				this.carta.add(i,c);
			}
		}
		if(c.getPalo()==Palo.ESPADAS) {
			i=0;
			while(this.carta.get(i).getPalo()==Palo.BASTOS && !this.carta.contains(c)) {
				while(this.carta.get(i).getNumero()<c.getNumero()) {
					i++;
				}
				this.carta.add(i,c);
			}
		}
		if(c.getPalo()==Palo.COPAS) {
			i=0;
			while(this.carta.get(i).getPalo()==Palo.BASTOS && !this.carta.contains(c)) {
				while(this.carta.get(i).getNumero()<c.getNumero()) {
					i++;
				}
				this.carta.add(i,c);
			}
		}else {
			i=0;
			while(this.carta.get(i).getPalo()==Palo.OROS && !this.carta.contains(c)) {
				while(this.carta.get(i).getNumero()<c.getNumero()) {
					i++;
				}
				this.carta.add(i,c);
			}
		}
	}
}
