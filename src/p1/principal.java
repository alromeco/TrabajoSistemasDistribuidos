package p1;

import java.util.Scanner;

public class principal {
	public static void main(String[] args) {
		int j1=0,j2=0;
		while(j1+j2<2) {
			System.out.println("Número de personas: ");
			Scanner teclado = new Scanner(System.in);
			j1 = teclado.nextInt();
			System.out.println("Número de jugadores controlados por la CPU: ");
			j2= teclado.nextInt();
			if(j1+j2<2) {
				System.out.println("Se requieren un mínimo de 2 jugadores para empezar la partida.");
			}
		}
		Deck b=new Deck();
		b.startDeck();
		Table m=new Table();
		Game p= new Game(m,j1,j2,b);
		System.out.println("Empieza la partida:");
		System.out.println("-----------------------------");
		p.startGame(j1+j2);
		
	}
}
