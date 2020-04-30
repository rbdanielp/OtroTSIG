package uy.tsig.grupo12.entidades.Usuario;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "Usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario implements Serializable {

    @Id
    @Column(unique = true)
    private String loginUser;

    @Column(nullable = false)
    private String loginPass;

    public Usuario() {
    }

    public Usuario(String loginUser, String loginPass) {
        this.loginUser = loginUser;
        this.loginPass = loginPass;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

}
