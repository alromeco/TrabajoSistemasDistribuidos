package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import p1.Card;
import p1.Deck;
import p1.Game;
import p1.Person;
import p1.Player;
import p1.Stick;
import p1.Table;

public class ManejadorClientes implements Runnable{
	
	private Socket[] socket;
	private ObjectInputStream[] inputStream;
	private ObjectOutputStream[] outputStream;
	private Player[] players;
	
	public ManejadorClientes(Socket[]  socket) throws IOException{
		this.socket=socket;
		for(int i=0;i<2;i++) {
			this.inputStream[i]=new ObjectInputStream(this.socket[i].getInputStream());
			this.outputStream[i]=new ObjectOutputStream(this.socket[i].getOutputStream());
			}
		}
	
	public void run() {
		boolean isConnected=true;
		while (isConnected) {
			try {
				isConnected=this.elegirOpcion(isConnected);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean elegirOpcion(boolean isConnected) throws IOException {
		int i = this.inputStream[0].readInt();
		int x = this.inputStream[1].readInt();
		if(i==1 && x==1) {
			this.jugar();
		} else {
			isConnected=false;
		}
		return isConnected;
			
	}
		
	
	private void jugar() {
		Deck b=new Deck();
		b.startDeck();
		Table table=new Table();
		for(int i=0;i<2;i++) {
			Player j=new Person();
			this.players[i]=j;
		}
		int i;
		int turn=0;
		boolean is=false;
		b.deal(this.players, 2);
		Card c=new Card(Stick.GOLDS,5);
		while(turn<2 && !is) {
			is=players[turn].hasCard(c);
			if(!is) {
				turn++;
			}
		}
		if(is) {
			i=turn+1;
			try {
				this.outputStream[turn].writeChars("Player "+i+" turn "+" , You are starting with the 5 of golds:");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table.add(players[turn].fiveGolds());
			for(int x=0;x<2;x++) {
				try {
					this.outputStream[x].writeObject(table);
					this.outputStream[turn].writeChars("---------------------");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(turn==2-1) {
				turn=0;
			}else {
				turn++;
			}
		}else {
			Random r=new Random();
			int a=r.nextInt(2-1);
			turn=a;
		}
		this.continueGame(turn,2,table,b);
	}
	
	public void continueGame(int turn,int playrs,Table table,Deck b) {
		//PRE: The game must be started, turn is the turn of the player that plays now and playrs the number of players.
		//POS: Players put cards on the deck, following the rules of cinquillo, 
		//			until someone runs out of cards, who is the winner.
		Card c=new Card();
		boolean end=false;
		while(!end) {
			int x=turn+1;
			this.outputStream[turn].writeChars("Player "+x+" turn:");
			this.outputStream[turn].writeObject(this.players[turn]);
			c=(Card)this.inputStream[turn].readObject();
			if(c==null) {
				if(b.numCards()!=0) {
					this.outputStream[turn].writeObject(b);
					this.players[turn].steal(this.deck);
					System.out.println("You take a card of the deck.");
					System.out.println(" ");
				}
			}else {
				this.table.add(c);
				end=this.players[turn].ended();
			}
			if(!end)
				turn++;
			if(turn==playrs && !end)
				turn=0;
			this.table.show();
			System.out.println("---------------------");
		}
		int x=turn+1;
		System.out.println("Player "+ x + " wins");
	}
}
