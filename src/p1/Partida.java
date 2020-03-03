package p1;

import java.util.Random;

public class Partida {
	private Mesa mesa;
	private Jugador[] jugadores;
	private Baraja baraja;
	
	public Partida(Mesa m, int pers,int cpu, Baraja b) {
		//PRE: La mesa m y la Baraja b deben estar creadas y b no debe estar vacía. pers y cpu deben ser enteros positivos o 0
		//POS: Crea una Partida con Mesa m, pers personas reales, cpu jugadores de la cpu y baraja b.
		this.mesa=m;
		this.jugadores=new Jugador[pers+cpu];
		this.baraja=b;
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
		//PRE: La Partida debe estar creada y jug debe ser un entero mayor que 1.
		//POS: Empieza la partida colocando el 5 de oros y, si no lo tiene nadie, otro 5.
		int i;
		int turno=0;
		boolean esta=false;
		this.baraja.repartir(this.jugadores, jug);
		Carta c=new Carta(Palo.OROS,5);
		while(turno<jug && !esta) {
			esta=jugadores[turno].tieneCarta(c);
			if(!esta) {
				turno++;
			}
		}
		if(esta) {
			i=turno+1;
			System.out.println("Turno del jugador " + i +" , juega el 5 de oros:");
			this.mesa.anadir(jugadores[turno].cincoOros());
			this.mesa.mostrar();
			System.out.println("---------------------");
			if(turno==jug-1) {
				turno=0;
			}else {
				turno++;
			}
		}else {
			Random r=new Random();
			int a=r.nextInt(jug-1);
			turno=a;
		}
		this.seguirPartida(turno,jug);
	}
	
	public void seguirPartida(int turno,int jug) {
		//PRE: La partida debe estar empezada, turno es el turno del jugador al que le toca y jug el número de jugadores.
		//POS: Los Jugadores van colocando cartas en la mesa, siguiendo las normas del cinquillo, 
		//			hasta que alguien se queda sin cartas, que es el ganador.
		Carta c=new Carta();
		boolean fin=false;
		while(!fin) {
			int x=turno+1;
			System.out.println("Turno del jugador " + x +":");
			c=jugadores[turno].elegirCarta(this.mesa);
			if(c==null) {
				if(this.baraja.numCartas()!=0) {
					this.jugadores[turno].robar(this.baraja);
					System.out.println("Robas carta");
					System.out.println(" ");
				}
			}else {
				this.mesa.anadir(c);
				fin=this.jugadores[turno].Finalizado();
			}
			if(!fin)
				turno++;
			if(turno==jug && !fin)
				turno=0;
			this.mesa.mostrar();
			System.out.println("---------------------");
		}
		int x=turno+1;
		System.out.println("Gana el jugador "+ x);
	}
}
