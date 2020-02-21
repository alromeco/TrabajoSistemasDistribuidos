package p1;

public class Partida {
	private Mesa mesa;
	private Jugador[] jugadores;
	private Baraja baraja;
	
	public Partida(Mesa m, int i, Baraja b) {
		this.mesa=m;
		this.jugadores=new Jugador[8];
		this.baraja=b;
	}
}
