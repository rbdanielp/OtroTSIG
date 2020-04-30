/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.persistencia;

import uy.tsig.grupo12.persistencia.controller.Controller;
import uy.tsig.grupo12.persistencia.interfaces.IPersistencia;

/**
 *
 * @author tecnoinf
 */
public class FachadaPersistencia {

    private static IPersistencia _controlador;

    public static IPersistencia getPersistencia() {
        if (_controlador == null) {
            _controlador = new Controller();
        }
        return _controlador;
    }

}
