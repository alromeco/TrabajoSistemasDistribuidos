package p1;

import java.util.Random;

public class Partida {
	private Mesa mesa;
	private Jugador[] jugadores;
	private Baraja baraja;
	private int turno;
	
	public Partida(Mesa m, int pers,int cpu, Baraja b) {
		this.mesa=m;
		this.jugadores=new Jugador[pers+cpu];
		this.baraja=b;
		this.turno=0;
		for(int i=0;i<pers;i++) {
			Jugador j=new Persona();
			this.jugadores[i]=j;
		}
		for(int i=pers;i<pers+cpu;i++) {
			Jugador j=new Cpu();
			this.jugadores[i]=j;
		}
	}
	public void empezarPartida(int jug) {
		int i=0;
		int turno;
		boolean esta=false;
		this.baraja.repartir(this.jugadores, jug);
		Carta c=new Carta(Palo.OROS,5);
		while(i<jug && !esta) {
			esta=jugadores[i].tieneCarta(c);
		}
		if(esta) {
			turno=i;
		}else {
			Random r=new Random();
			int a=r.nextInt(jug-1);
			turno=a;
		}
		this.seguirPartida(turno,jug);
	}
	public void seguirPartida(int turno,int jug) {
		Carta c=new Carta();
		while(!this.jugadores[turno].Finalizado()) {
			c=jugadores[turno].elegirCarta(this.mesa);
			if(c==null) {
				this.jugadores[turno].robar(this.baraja);
			}else {
				this.mesa.anadir(c);
			}
			turno++;
			if(turno==jug)
				turno=0;
			this.mesa.mostrar();
		}
		System.out.println("Gana el jugador "+ turno);
	}
}
