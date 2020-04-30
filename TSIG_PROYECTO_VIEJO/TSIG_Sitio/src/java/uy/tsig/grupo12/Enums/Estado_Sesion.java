/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.Enums;

/**
 *
 * @author tecnoinf
 */
public enum Estado_Sesion {
    ACTIVA, //Sesion iniciada
    NO_ACTIVA, //Sin sesion
    ERROR //Error al intentar iniciar sesion (nick o password incorrectos)
}
