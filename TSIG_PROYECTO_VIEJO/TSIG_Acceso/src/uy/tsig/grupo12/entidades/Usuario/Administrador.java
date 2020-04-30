/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.entidades.Usuario;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author tecnoinf
 */
@Entity(name = "Administrador")
public class Administrador extends Usuario implements Serializable {

    public Administrador() {
    }

    public Administrador(String loginUser, String loginPass) {
        super(loginUser, loginPass);
    }

}
