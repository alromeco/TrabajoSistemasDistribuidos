package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import p1.Deck;
import p1.Player;
import p1.Table;

public class Cliente {
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private Table table;
	private Deck b;
	private Player p;
	public Cliente() {
		try {
			this.socket=new Socket("localhost",5000);
			System.out.println("Constructor1");
			this.inputStream=new ObjectInputStream(this.socket.getInputStream());
			System.out.println("Constructor2");
			this.outputStream=new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void opcionServidor(int i) {
		try {
			this.outputStream.writeInt(i);
			int x=this.inputStream.readInt();
			if(x==1) {
				System.out.println("Jugar");
				this.jugar();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void jugar() {
		try {
			System.out.println(this.inputStream.readLine());
			if(this.inputStream.readBoolean()) {
				System.out.println(this.inputStream.readLine());
			}
			this.table=(Table) this.inputStream.readObject();
			this.table.show();
			System.out.println("------------------");
			boolean end=false;
			while(!end) {
				System.out.println(this.inputStream.readLine());
				this.p=(Player) this.inputStream.readObject();
				if(this.p.canPlay(table)) {
					this.outputStream.writeObject(p.chooseCard(table));
					this.outputStream.writeObject(this.p);
					end=this.inputStream.readBoolean();
				}else {
					this.b=(Deck) this.inputStream.readObject();
					if(b.numCards()!=0) {
						this.p.steal(b);
						System.out.println("You take a card of the deck.");
						System.out.println(" ");
						this.outputStream.writeObject(this.p);
					}
				}
				this.table=(Table) this.inputStream.readObject();
				this.table.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
