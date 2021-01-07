package conexiones;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
	private ServerSocket servidor;
	Socket clientes[]=new Socket[2];
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
			Socket cliente1=this.servidor.accept();
			Socket cliente2=this.servidor.accept();
			this.clientes[0]=cliente1;
			this.clientes[1]=cliente2;
			
			new Thread(new ManejadorClientes(clientes)).start();
		}
	}
}
