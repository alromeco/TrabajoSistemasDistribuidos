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
	private DataInputStream[] inputStream=new DataInputStream[2];
	private DataOutputStream[] outputStream=new DataOutputStream[2];
	private Player[] players=new Player[2];
	
	public ManejadorClientes(Socket[]  socket) throws IOException{
		this.socket=socket;
		for(int i=0;i<2;i++) {
			this.inputStream[i]=new DataInputStream(this.socket[i].getInputStream());
			this.outputStream[i]=new DataOutputStream(this.socket[i].getOutputStream());
			}
		}
	
	public void run() {
		try {
			boolean isConnected=true;
			while(isConnected) {
			isConnected=this.elegirOpcion(isConnected);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}else {
			if(i==2 || x==2) {
				for(int y=0;y<2;y++) {
					this.socket[y].close();
					isConnected=false;
				}
			}
			
		}
		return isConnected;
			
	}
		
	
	private void jugar() {
		try {
			Deck b=new Deck();
			int jug;
			b.startDeck();
			Table table=new Table();
			for(int i=0;i<2;i++) {
				Player j=new Person();
				this.players[i]=j;
			}
			for(int y=0;y<2;y++) {
				jug=y+1;
				this.outputStream[y].writeBytes("You are the player "+jug+"\r\n");
				
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
			for(int y=0;y<2;y++) {
				this.outputStream[y].writeBoolean(is);
			}
			if(is) {
				i=turn+1;
				for(int y=0;y<2;y++) {
					this.outputStream[y].writeBytes("Player "+i+" turn "+" , it starts with the 5 of golds: \r\n");
				}
				table.add(players[turn].fiveGolds());
				for(int x=0;x<2;x++) {
					this.outputStream[x].writeBytes(table.show()+"-1\r\n");
							System.out.println(table.show());
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
		boolean end=false;
		Card card=null;
		while(!end) {
			int x=turn+1;
			for(int y=0;y<2;y++) {
				this.outputStream[y].writeBytes("Player "+x+" turn: \r\n");
				if(y==turn) {
					this.outputStream[y].writeBoolean(true);
				}else {
					this.outputStream[y].writeBoolean(false);
				}
			}
			this.outputStream[turn].writeBytes(this.players[turn].show()+"-1\r\n");
			boolean juega=this.players[turn].canPlay(table);
			this.outputStream[turn].writeBoolean(juega);
			Integer c;
			if(juega) {
				c=this.inputStream[turn].readInt();
				juega=false;
				if(c<=this.players[turn].numCards()) {
					juega=this.players[turn].canPlayCard(table,c);
				}
				this.outputStream[turn].writeBoolean(juega);
				while(!juega){
					c=this.inputStream[turn].readInt();
					if(c<=this.players[turn].numCards()) {
						juega=this.players[turn].canPlayCard(table,c);
					}
					this.outputStream[turn].writeBoolean(juega);
				}
				card=this.players[turn].chooseCard(table,c);
			}else {
				c=null;
			}
			if(c==null) {
				juega=b.numCards()!=0;
				this.outputStream[turn].writeBoolean(juega);
				if(juega) {
					this.players[turn].steal(b);
					this.outputStream[turn].writeBytes(this.players[turn].show()+"-1\r\n");				
				}
			}else {
				table.add(card);
				end=this.players[turn].ended();
			}
			if(!end)
				turn++;
			if(turn==playrs && !end)
				turn=0;
			for(int y=0;y<2;y++) {
				this.outputStream[y].writeBoolean(end);
				this.outputStream[y].writeBytes(table.show()+"-1\r\n");
			}
		}
		int x=turn+1;
		for(int y=0;y<2;y++) {
			this.outputStream[y].writeBytes("Player "+ x + " wins\r\n");
		}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
