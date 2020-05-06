package uy.tsig.grupo16.util;

import uy.tsig.grupo16.entidades.Administrador;
import uy.tsig.grupo16.entidades.Rol;
import uy.tsig.grupo16.entidades.Usuario;
import uy.tsig.grupo16.logica.FachadaLogica;
import uy.tsig.grupo16.logica.LogicaException;

public class MainApp {

	

	public static void main(String[] args) {

		
		//**************************************************************
		Loguear.logTitulo("MainApp: Creando Usuario...");
		
		Usuario usuario;

		

		try {
			// int i = 1;
			// usuario = new Administrador(i, "Primer", "Pwd", i);
			Rol r = null;
			String i = "1";
			usuario = new Administrador(i, "Usu"+i, "Pwd"+i, r.ADMINISTRADOR);

			FachadaLogica.getUsuarioController().alta(usuario);
		} catch (LogicaException e) {
			Loguear.errorConTitulo("Error al crear un usuario.");
			e.printStackTrace();
		}

		
		Loguear.logTitulo("MainApp: Usuario registrado...");
		//**************************************************************
	}

}
