package p1;

import java.util.Scanner;

public class principal {
	public static void main(String[] args) {
		int j1=0,j2=0;
		while(j1+j2<2) {
			System.out.println("Number of people: ");
			Scanner keyboard = new Scanner(System.in);
			j1 = keyboard.nextInt();
			System.out.println("Number of CPU players: ");
			j2= keyboard.nextInt();
			if(j1+j2<2) {
				System.out.println("It is required a minimum of 2 players.");
			}
		}
		Deck b=new Deck();
		b.startDeck();
		Table m=new Table();
		Game p= new Game(m,j1,j2,b);
		System.out.println("The game starts:");
		System.out.println("-----------------------------");
		p.startGame(j1+j2);
		
	}
}
