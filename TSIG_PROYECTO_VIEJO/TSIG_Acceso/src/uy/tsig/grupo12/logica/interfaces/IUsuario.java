/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.logica.interfaces;

import uy.tsig.grupo12.entidades.Usuario.*;
import uy.tsig.grupo12.logica.LogicaException;

/**
 *
 * @author tecnoinf
 */
public interface IUsuario {

    Usuario buscarUsuario(String nombreUsuario) throws LogicaException;
    
    Usuario buscarUsuarioId(String id) throws LogicaException;

    Usuario login(String nombreUsuario, String password) throws LogicaException;

    void alta(Usuario u) throws LogicaException;

    void modificar(Usuario u) throws LogicaException;
}
