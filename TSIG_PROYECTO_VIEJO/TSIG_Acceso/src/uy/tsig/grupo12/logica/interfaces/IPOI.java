/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.logica.interfaces;

import java.util.ArrayList;
import java.util.List;
import uy.tsig.grupo12.entidades.POI.*;
import uy.tsig.grupo12.logica.LogicaException;

/**
 *
 * @author tecnoinf
 */
public interface IPOI {

    public POI buscarPOI(int id) throws LogicaException;
    
    public POI buscarPoiNom(String nom) throws LogicaException;

    public void altaPOI(POI poi) throws LogicaException;
    
    public Comentario buscarComentario(int id) throws LogicaException;
    
    public void altaComentario(Comentario com) throws LogicaException;

    public void modificarPOI(POI poi) throws LogicaException;
    
    public ArrayList<POI> poiList() throws LogicaException;
    
    public List<Estadio> listarEstadios() throws LogicaException;

}
