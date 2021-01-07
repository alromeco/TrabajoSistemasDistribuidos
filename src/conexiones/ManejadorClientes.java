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
	private ObjectInputStream[] inputStream=new ObjectInputStream[2];
	private ObjectOutputStream[] outputStream=new ObjectOutputStream[2];
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
			for(int y=0;y<2;y++) {
				this.outputStream[y].writeInt(1);
			}
			this.jugar();
			
		} else {
			isConnected=false;
		}
		return isConnected;
			
	}
		
	
	private void jugar() {
		try {
			Deck b=new Deck();
			b.startDeck();
			Table table=new Table();
			for(int i=0;i<2;i++) {
				Player j=new Person();
				this.players[i]=j;
			}
			for(int y=0;y<2;y++) {
				this.outputStream[y].writeBytes("You are the player "+y+1);
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
				for(int y=0;y<2;y++) {
					this.outputStream[y].writeBoolean(is);
				}
				i=turn+1;
				for(int y=0;y<2;y++) {
					this.outputStream[y].writeBytes("Player "+i+" turn "+" , it starts with the 5 of golds:");
				}
				table.add(players[turn].fiveGolds());
				for(int x=0;x<2;x++) {
					this.outputStream[x].writeObject(table);
			    }
				if(turn==1) {
					turn=0;
				}else {
					turn++;
				}
			}else {
				Random r=new Random();
				int a=r.nextInt(1);
				turn=a;
			}
			this.continueGame(turn,2,table,b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void continueGame(int turn,int playrs,Table table,Deck b) {
		try {
		Card c=new Card();
		boolean end=false;
		while(!end) {
			int x=turn+1;
			for(int y=0;y<2;y++) {
				this.outputStream[y].writeBytes("Player "+x+" turn:");
			}
			this.outputStream[turn].writeObject(this.players[turn]);
			if(this.players[turn].canPlay(table)) {				
				c=(Card)this.inputStream[turn].readObject();
			}else {
				c=null;
			}
			if(c==null) {
				this.outputStream[turn].writeObject(b);
				if(b.numCards()!=0) {
					this.players[turn]=(Player) this.inputStream[turn].readObject();
				}
			}else {
				table.add(c);
				this.players[turn]=(Player) this.inputStream[turn].readObject();
				end=this.players[turn].ended();
				this.outputStream[turn].writeBoolean(end);
			}
			if(!end)
				turn++;
			if(turn==playrs && !end)
				turn=0;
			for(int y=0;y<2;y++) {
				this.outputStream[y].writeObject(table);
			}
		}
		int x=turn+1;
		System.out.println("Player "+ x + " wins");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
