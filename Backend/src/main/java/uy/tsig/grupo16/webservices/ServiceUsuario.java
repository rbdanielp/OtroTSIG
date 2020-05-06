package uy.tsig.grupo16.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.tsig.grupo16.entidades.Usuario;
import uy.tsig.grupo16.logica.FachadaLogica;
import uy.tsig.grupo16.logica.LogicaException;

//@Path("/Usuario")
//public class ServiceUsuario {
//	
//	@POST
//	@Path("/Login")
//	@Consumes({MediaType.APPLICATION_JSON})
//	@Produces({MediaType.APPLICATION_JSON})
//	public Usuario login(String usuario, String password) {
//		try {
//			return FachadaLogica.getUsuarioController().login(usuario, password);
//		} catch (LogicaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//}

@Path("/ServiceUsuario")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ServiceUsuario {
	
	@POST
	@Path("/Login/{nombre} ")
	public Usuario login(@PathParam("nombre") String usuario,  String password) {
		try {
			return FachadaLogica.getUsuarioController().login(usuario, password);
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}