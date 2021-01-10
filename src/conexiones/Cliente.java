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
import java.util.Scanner;

import p1.Deck;
import p1.Player;
import p1.Table;

public class Cliente {
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public static void main(String[] args) {
		Cliente c = new Cliente();
		Scanner sc = new Scanner(System.in);
		int i = 0;
		while (i != 2) {
			System.out.println("1.Jugar");
			i = sc.nextInt();
			c.opcionServidor(i);
		}
		
	}

	public Cliente() {
		try {
			this.socket = new Socket("localhost", 5000);
			this.inputStream = new DataInputStream(this.socket.getInputStream());
			this.outputStream = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void opcionServidor(int i) {
		try {
			this.outputStream.writeInt(i);
			int x = this.inputStream.readInt();
			if (x == 1) {
				System.out.println("Jugar");
				this.jugar();
			}
			if(x==2) {
				this.inputStream.close();
				this.outputStream.close();
				this.socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void jugar() {
		try {
			boolean juega;
			Scanner sc = new Scanner(System.in);
			String s = null;
			System.out.println(this.inputStream.readLine());
			if (this.inputStream.readBoolean()) {
				System.out.println(this.inputStream.readLine());
				s = this.inputStream.readLine();
				//
				while (!s.equals("-1")) {
					System.out.println(s);
					s = this.inputStream.readLine();
				}
				System.out.println("------------------");
			}
			boolean end = false;
			while (!end) {
				System.out.println(this.inputStream.readLine());
				if(this.inputStream.readBoolean()) {
					s = this.inputStream.readLine();
					while (!s.equals("-1")) {
						System.out.println(s);
						s = this.inputStream.readLine();
					}
					if (this.inputStream.readBoolean()) {
						int c = sc.nextInt();
						this.outputStream.writeInt(c);
						juega = this.inputStream.readBoolean();
						while (!juega) {
							System.out.println("That card can not be played, choose other: ");
							c = sc.nextInt();
							this.outputStream.writeInt(c);
							juega = this.inputStream.readBoolean();
						}
						end = this.inputStream.readBoolean();
					} else {
						juega = this.inputStream.readBoolean();
						if (juega) {
							System.out.println("You take a card of the deck.");
							System.out.println(" ");
							s = this.inputStream.readLine();
							while (!s.equals("-1")) {
								System.out.println(s);
								s = this.inputStream.readLine();
							}
						}
					}
				}else {
					end = this.inputStream.readBoolean();
				}
				s = this.inputStream.readLine();
				while (!s.equals("-1")) {
					System.out.println(s);
					s = this.inputStream.readLine();
				}
				System.out.println("------------------");
			}
			System.out.println(this.inputStream.readLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
