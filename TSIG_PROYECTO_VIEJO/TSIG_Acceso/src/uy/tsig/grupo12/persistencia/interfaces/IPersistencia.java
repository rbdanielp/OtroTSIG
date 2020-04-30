/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.persistencia.interfaces;

import java.util.HashMap;
import java.util.List;
import uy.tsig.grupo12.entidades.POI.Estadio;

/**
 *
 * @author tecnoinf
 */
public interface IPersistencia {

    List<Object> buscar(Class<?> tipo, String nombreQuery, HashMap<String, Object> parametros);

    Object buscar(Class<?> tipo, Object id);
    
    Object buscarNom(Class<?> tipo, Object nombre);

    void alta(Object o);

    void modificar(Object o);

    void baja(Object o);
    

}
