/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.logica.controller;

import uy.tsig.grupo12.entidades.Usuario.Usuario;
import uy.tsig.grupo12.logica.LogicaException;
import uy.tsig.grupo12.logica.interfaces.IUsuario;
import uy.tsig.grupo12.persistencia.FachadaPersistencia;

/**
 *
 * @author tecnoinf
 */
public class UsuarioController implements IUsuario {

    @Override
    public Usuario buscarUsuario(String nombreUsuario) throws LogicaException {
        return (Usuario) FachadaPersistencia.getPersistencia().buscar(Usuario.class, nombreUsuario);
    }
    
    @Override
    public Usuario buscarUsuarioId(String id) throws LogicaException {
        return (Usuario) FachadaPersistencia.getPersistencia().buscar(Usuario.class, id);
    }

    @Override
    public Usuario login(String nombreUsuario, String password) throws LogicaException {
        Usuario u = buscarUsuario(nombreUsuario);

        if (u.getLoginPass().equals(password)) {
            return u;
        } else {
            return null;
        }

    }

    @Override
    public void alta(Usuario u) throws LogicaException {
        if (buscarUsuario(u.getLoginUser()) != null) {
            throw new LogicaException("El Usuario ya existe");
        }

        FachadaPersistencia.getPersistencia().alta(u);
    }

    @Override
    public void modificar(Usuario u) throws LogicaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
