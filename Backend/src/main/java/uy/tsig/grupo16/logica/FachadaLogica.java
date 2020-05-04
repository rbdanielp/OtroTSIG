package uy.tsig.grupo16.logica;

import uy.tsig.grupo16.logica.controller.UsuarioController;
import uy.tsig.grupo16.logica.interfaces.IUsuario;

public class FachadaLogica {

	private static IUsuario _controladorUsuario;
	
    public static IUsuario getUsuarioController() {
        if (_controladorUsuario == null) {
            _controladorUsuario = new UsuarioController();
        }
        return _controladorUsuario;
    }
	
}
