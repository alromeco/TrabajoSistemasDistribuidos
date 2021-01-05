package lanzarConexiones;

import java.io.IOException;

import conexiones.Servidor;

public class RunServidor {
	public static void main(String args[]) {
		Servidor s=new Servidor();
		try {
			s.empezarEscucha();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
