/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.logica.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.POIJpaController;
import uy.tsig.grupo12.entidades.POI.*;
import uy.tsig.grupo12.entidades.Usuario.Usuario;
import uy.tsig.grupo12.logica.LogicaException;
import uy.tsig.grupo12.logica.interfaces.IPOI;
import uy.tsig.grupo12.persistencia.FachadaPersistencia;

/**
 *
 * @author tecnoinf
 */
public class POIController implements IPOI {
    EntityManagerFactory fact = Persistence.createEntityManagerFactory("TSIG_GP12PU");
    
    @Override
    public POI buscarPOI(int id) throws LogicaException {
        return (POI) FachadaPersistencia.getPersistencia().buscar(POI.class, id);
    }
    
     public POI buscarPoiNom(String nom) throws LogicaException {
        return  (POI) FachadaPersistencia.getPersistencia().buscarNom(POI.class, nom);
    }

    @Override
    public void altaPOI(POI poi) throws LogicaException {
        if (buscarPOI(poi.getGid()) != null) {
            throw new LogicaException("El poi ya existe");
        }

        FachadaPersistencia.getPersistencia().alta(poi);
    }
    
    @Override
    public Comentario buscarComentario(int id) throws LogicaException {
        return (Comentario) FachadaPersistencia.getPersistencia().buscar(Comentario.class, id);
    }
        @Override
    public void altaComentario(Comentario com) throws LogicaException {
        if (buscarComentario(com.getId()) != null) {
            throw new LogicaException("El Comentario ya existe");
        }

        FachadaPersistencia.getPersistencia().alta(com);
    }

    @Override
    public void modificarPOI(POI poi) throws LogicaException {
        if (buscarPOI(poi.getGid()) != null) {
            throw new LogicaException("El poi ya existe");
        }

        FachadaPersistencia.getPersistencia().modificar(poi);
    }
    
    @Override
    public ArrayList<POI> poiList() throws LogicaException {
        
        POIJpaController poiCtrl = new POIJpaController(fact);

        return new ArrayList(poiCtrl.findPOIEntities());

    }
    
     @Override
    public List<Estadio> listarEstadios() throws LogicaException{
        List<POI> pois=null;
        List<Estadio> estadios= null;
        try {
            pois = poiList();
            if(pois!=null){
                boolean emptyEstadios=true;
                for(POI p: pois){
                    if(p instanceof Estadio){
                        
                        if(emptyEstadios){
                                estadios= new ArrayList<>();
                                estadios.add((Estadio)p);
                                emptyEstadios=false;
                            }
                            else{
                                estadios.add((Estadio)p);
                            }
                    }
                }
            }
        } catch (LogicaException ex) {
            Logger.getLogger(POIController.class.getName()).log(Level.SEVERE, null, ex);
        }

            return estadios;
    }
}
    
