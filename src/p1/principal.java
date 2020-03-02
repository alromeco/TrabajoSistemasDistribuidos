package p1;

public class principal {
	public static void main(String[] args) {
		
		Baraja b=new Baraja();
		b.iniciarBaraja();
		Mesa m=new Mesa();
		Partida p= new Partida(m,0,2,b);
		p.empezarPartida(2);
		
	}
}
