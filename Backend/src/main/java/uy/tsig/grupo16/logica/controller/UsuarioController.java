package uy.tsig.grupo16.logica.controller;

import java.util.logging.Logger;

import uy.tsig.grupo16.entidades.Usuario;
import uy.tsig.grupo16.logica.LogicaException;
import uy.tsig.grupo16.logica.interfaces.IUsuario;
import uy.tsig.grupo16.persistencia.FachadaPersistencia;
import uy.tsig.grupo16.util.Loguear;

public class UsuarioController implements IUsuario  {
	

	public Usuario buscarUsuario(String nombreUsuario) throws LogicaException {
		return (Usuario) FachadaPersistencia.getPersistencia().buscar(Usuario.class, nombreUsuario);
	}

//	public Usuario buscarUsuarioId(String id) throws LogicaException {
//		return (Usuario) FachadaPersistencia.getPersistencia().buscar(Usuario.class, id);
//	}
	
	public Usuario buscarUsuarioId(int id) throws LogicaException {
	return (Usuario) FachadaPersistencia.getPersistencia().buscar(Usuario.class, id);
}
	
    public void alta(Usuario u) throws LogicaException {
    	//Loguear.logTitulo("UsuarioController: Alta");
    	
    	if (buscarUsuario(u.getUsuarioNombre() ) != null) {
            throw new LogicaException("El Usuario ya existe");
        }
        FachadaPersistencia.getPersistencia().alta(u);
    }

	
	public Usuario login(String nombreUsuario, String password) throws LogicaException {
        Usuario u = buscarUsuario(nombreUsuario);

        if (u.getUsuarioPassword().equals(password)) {
            return u;
        } else {
            return null;
        }
	}

    
}
