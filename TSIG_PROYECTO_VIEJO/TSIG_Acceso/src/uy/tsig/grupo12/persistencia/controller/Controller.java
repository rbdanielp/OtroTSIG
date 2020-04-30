/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.persistencia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.POIJpaController;
import uy.tsig.grupo12.entidades.POI.POI;
import uy.tsig.grupo12.logica.LogicaException;
import uy.tsig.grupo12.persistencia.interfaces.IPersistencia;

/**
 *
 * @author tecnoinf
 */
public class Controller implements IPersistencia {

    @Override
    public List<Object> buscar(Class<?> tipo, String nombreQuery, HashMap<String, Object> parametros) {
        Query query = Manejador.getEntityManager().createNamedQuery(nombreQuery);
        if (parametros != null) {
            parametros.forEach((k, v) -> query.setParameter(k, v));
        }

        return query.getResultList();
    }

    @Override
    public Object buscar(Class<?> tipo, Object id) {
        return Manejador.getEntityManager().find(tipo, id);
    }

    @Override
    public Object buscarNom(Class<?> tipo, Object nombre) {
        return Manejador.getEntityManager().find(tipo, nombre);
    }

    @Override
    public void alta(Object o) {
        Manejador.getEntityManager().getTransaction().begin();
        Manejador.getEntityManager().persist(o);
        Manejador.getEntityManager().getTransaction().commit();

        Manejador.getEntityManager().clear();//ver si no da problemas
    }

    @Override
    public void modificar(Object o) {
        Manejador.getEntityManager().getTransaction().begin();
        Manejador.getEntityManager().merge(o);
        Manejador.getEntityManager().getTransaction().commit();

        Manejador.getEntityManager().clear();//ver si no da problemas
    }

    @Override
    public void baja(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
