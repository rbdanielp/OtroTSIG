/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.logica;

import uy.tsig.grupo12.logica.controller.POIController;
import uy.tsig.grupo12.logica.controller.PartidosController;
import uy.tsig.grupo12.logica.controller.UsuarioController;
import uy.tsig.grupo12.logica.interfaces.IPOI;
import uy.tsig.grupo12.logica.interfaces.IPartidos;
import uy.tsig.grupo12.logica.interfaces.IUsuario;

/**
 *
 * @author tecnoinf
 */
public class FachadaLogica {

    private static IPOI _controladorPOI;
    private static IUsuario _controladorUsuario;
    private static IPartidos _controladorPartido;

    public static IPOI getPOIController() {
        if (_controladorPOI == null) {
            _controladorPOI = new POIController();
        }
        return _controladorPOI;
    }

    public static IUsuario getUsuarioController() {
        if (_controladorUsuario == null) {
            _controladorUsuario = new UsuarioController();
        }
        return _controladorUsuario;
    }

    public static IPartidos getPartidoController() {
        if (_controladorPartido == null) {
            _controladorPartido = new PartidosController();
        }
        return _controladorPartido;
    }
}
