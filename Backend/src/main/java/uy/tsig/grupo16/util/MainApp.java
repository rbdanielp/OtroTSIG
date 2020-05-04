package uy.tsig.grupo16.util;

import java.util.Scanner;


import uy.tsig.grupo16.entidades.Administrador;
import uy.tsig.grupo16.entidades.Usuario;
import uy.tsig.grupo16.logica.FachadaLogica;
import uy.tsig.grupo16.logica.LogicaException;

public class MainApp {

	public static void main(String[] args)  {
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		
		Usuario usuario;
		
		
		while (opcion!=5) {
			System.out.println("1. Crear Usuario");
//			System.out.println("2. Buscar Producto");
//			System.out.println("3. Actualizar Producto");
//			System.out.println("4. Eliminar Producto");
//			System.out.println("5. Salir");
			System.out.println("Elija una opción:");
			
			
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Creando Usuario...");
				
				try {
					int i = 1;
					usuario = new Administrador(i, "Primer", "Pwd", i);
					FachadaLogica.getUsuarioController().alta(usuario);
				} catch (LogicaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Usuario registrado..");
				System.out.println();
				break;
				
			}
		}

	}

}
