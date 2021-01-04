package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	public Cliente() {
		try {
			this.socket=new Socket("localhost",5000);
			this.inputStream=new DataInputStream(this.socket.getInputStream());
			this.outputStream=new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void opcionServidor(int i) {
		try {
			this.outputStream.writeInt(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
