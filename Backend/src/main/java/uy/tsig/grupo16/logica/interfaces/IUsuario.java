package uy.tsig.grupo16.logica.interfaces;

import uy.tsig.grupo16.entidades.Usuario;
import uy.tsig.grupo16.logica.LogicaException;

public interface IUsuario {

	Usuario login(String nombreUsuario, String password) throws LogicaException;
	
    Usuario buscarUsuario(String nombreUsuario) throws LogicaException;
    
    //Usuario buscarUsuarioId(String id) throws LogicaException;
    Usuario buscarUsuarioId(int id) throws LogicaException;
    
	void alta(Usuario u) throws LogicaException;
}
