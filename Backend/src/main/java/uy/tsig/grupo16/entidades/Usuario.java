package uy.tsig.grupo16.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Id;
import javax.persistence.Column;



@Entity(name = "Usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario implements Serializable {

     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(unique = true)
    private String UsuarioId;

    @Column(nullable = false)
    private String UsuarioNombre;
    
    @Column(nullable = false)
    private String UsuarioPassword;
    
    @Column(nullable = false)
    private String UsuarioRol;

    
    
    //////////////////////////////////////////////////////
    
	public Usuario() {
		
	}
	
    
	public Usuario(String usuarioId, String usuarioNombre, String usuarioPassword, String usuarioRol) {
		super();
		UsuarioId = usuarioId;
		UsuarioNombre = usuarioNombre;
		UsuarioPassword = usuarioPassword;
		UsuarioRol = usuarioRol;
	}
    
    

    //////////////////////////////////////////////////////

	public String getUsuarioId() {
		return UsuarioId;
	}



	public void setUsuarioId(String usuarioId) {
		UsuarioId = usuarioId;
	}

	public String getUsuarioNombre() {
		return UsuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		UsuarioNombre = usuarioNombre;
	}

	public String getUsuarioPassword() {
		return UsuarioPassword;
	}

	public void setUsuarioPassword(String usuarioPassword) {
		UsuarioPassword = usuarioPassword;
	}

	public String getUsuarioRol() {
		return UsuarioRol;
	}

	public void setUsuarioRol(String usuarioRol) {
		UsuarioRol = usuarioRol;
	}
    
    

    
    
    
}



//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX






//@Entity(name = "Usuario")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//public abstract class Usuario implements Serializable {
//
//     
//    //@GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Id
//    @Column(unique = true)
//    private int UsuarioId;
//
//    @Column(nullable = false)
//    private String UsuarioNombre;
//    
//    @Column(nullable = false)
//    private String UsuarioPassword;
//    
//    @Column(nullable = false)
//    private int UsuarioRol;
//
//    
//    
//    //////////////////////////////////////////////////////
//    
//	public Usuario() {
//		
//	}
//	
//    
//	public Usuario(int usuarioId, String usuarioNombre, String usuarioPassword, int usuarioRol) {
//		super();
//		UsuarioId = usuarioId;
//		UsuarioNombre = usuarioNombre;
//		UsuarioPassword = usuarioPassword;
//		UsuarioRol = usuarioRol;
//	}
//    
//    
//
//    //////////////////////////////////////////////////////
//
//	public int getUsuarioId() {
//		return UsuarioId;
//	}
//
//
//
//	public void setUsuarioId(int usuarioId) {
//		UsuarioId = usuarioId;
//	}
//
//	public String getUsuarioNombre() {
//		return UsuarioNombre;
//	}
//
//	public void setUsuarioNombre(String usuarioNombre) {
//		UsuarioNombre = usuarioNombre;
//	}
//
//	public String getUsuarioPassword() {
//		return UsuarioPassword;
//	}
//
//	public void setUsuarioPassword(String usuarioPassword) {
//		UsuarioPassword = usuarioPassword;
//	}
//
//	public int getUsuarioRol() {
//		return UsuarioRol;
//	}
//
//	public void setUsuarioRol(int usuarioRol) {
//		UsuarioRol = usuarioRol;
//	}
//    
//    
//
//    
//    
//    
//}
