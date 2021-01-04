package lanzarConexiones;

import java.util.Scanner;

import conexiones.Cliente;

public class RunCliente {
	public static void main(String[] args) {
		Cliente c=new Cliente();
		Scanner sc= new Scanner(System.in);
		int i=0;
		while(i !=2) {
			System.out.println("1.Jugar");
			System.out.println("2.Salir");
			i=sc.nextInt();
			c.opcionServidor(i);
		}
	}
}
