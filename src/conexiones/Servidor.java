package conexiones;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private ServerSocket servidor;
	public Servidor() {
		try {
			this.servidor=new ServerSocket(5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void empezarEscucha() throws IOException{
		while(true) {
			Socket cliente=this.servidor.accept();
		}
	}
}
