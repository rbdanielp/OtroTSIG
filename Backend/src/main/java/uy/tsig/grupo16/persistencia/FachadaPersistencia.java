package uy.tsig.grupo16.persistencia;

import uy.tsig.grupo16.persistencia.controller.Controller;
import uy.tsig.grupo16.persistencia.interfaces.IPersistencia;

public class FachadaPersistencia {

    private static IPersistencia _controlador;

    public static IPersistencia getPersistencia() {
        if (_controlador == null) {
            _controlador = new Controller();
        }
        return _controlador;
    }
    
}
