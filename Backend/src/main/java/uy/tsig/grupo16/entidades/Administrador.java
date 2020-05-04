package uy.tsig.grupo16.entidades;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity(name = "Administrador")
public class Administrador  extends Usuario implements Serializable {


	private static final long serialVersionUID = 1L;
	
    public Administrador() {
    }

//    public Administrador(int id, String usuario, String pwd, int rol) {
//        super(id, usuario, pwd, rol);
//    }
    
    public Administrador(String id, String usuario, String pwd, String rol) {
        super(id, usuario, pwd, rol);
    }
	
}
