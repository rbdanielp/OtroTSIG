/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.logica.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import uy.tsig.grupo12.datatypes.DtPosicion;
import uy.tsig.grupo12.entidades.Partidos.DtTipo;

import uy.tsig.grupo12.entidades.Partidos.EquipoPais;
import uy.tsig.grupo12.entidades.Partidos.Partido;
import uy.tsig.grupo12.logica.FachadaLogica;
import uy.tsig.grupo12.logica.LogicaException;
import uy.tsig.grupo12.logica.interfaces.IPartidos;
import uy.tsig.grupo12.persistencia.FachadaPersistencia;

/**
 *
 * @author tecnoinf
 */
public class PartidosController implements IPartidos {

    @Override
    public List< Partido> buscar(EquipoPais e) throws LogicaException {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("equipo", e);

        return (List< Partido>) (List) FachadaPersistencia.getPersistencia().buscar(Partido.class, "Partido.findByEquipo", parametros);
    }

    @Override
    public List<Partido> listar() throws LogicaException {
        return (List< Partido>) (List) FachadaPersistencia.getPersistencia().buscar(Partido.class, "Partido.findAll", null);
    }

    @Override
    public void alta(Partido p) throws LogicaException {
        FachadaPersistencia.getPersistencia().alta(p);
    }

    @Override
    public void alta(EquipoPais e) throws LogicaException {
        FachadaPersistencia.getPersistencia().alta(e);
    }

    @Override
    public void modificar(Partido p) throws LogicaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        @Override
    public List<EquipoPais> listarEquipos() throws LogicaException {
        return (List< EquipoPais>) (List) FachadaPersistencia.getPersistencia().buscar(EquipoPais.class, "EquipoPais.findAll", null);
    }

    public List<Partido> listarPorFecha(EquipoPais e) throws LogicaException{
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("equipo", e);

        List <Partido> list = (List< Partido>) (List) FachadaPersistencia.getPersistencia().buscar(Partido.class, "Partido.findByEquipoOrdASC", parametros);
        for(Partido p: list){
            System.out.println(p.getFecha().toString());
        }
        return list;
        //---------------------------
    }

    
@Override
    public List<Partido> listarPartidosJugados(EquipoPais e){
        List<Partido> lRes= null;
        if(e!=null){
            try{
                List<Partido> lp = listarPorFecha(e);

                if(lp!=null){
                    Date fechaActual= new Date();
                    boolean emptyLRes=true;
                    for(Partido p: lp){

                        if(p.getFecha().before(fechaActual)){//Si la fecha del partido es anterior a la fecha actual
                            if(emptyLRes){
                                lRes= new ArrayList<>();
                                lRes.add(p);
                                emptyLRes=false;
                            }
                            else{
                                lRes.add(p);
                            }
                        }
                    }
                }

            } catch (LogicaException ex) {
                Logger.getLogger(PartidosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{ //Listar todos los partidos con fecha anterior a la fecha actual
            List<Partido> lp = listarPorFecha();
            if(lp!=null){
                Date fechaActual= new Date();
                boolean emptyLRes=true;
                for(Partido p: lp){
                    
                    if(p.getFecha().before(fechaActual)){//Si la fecha del partido es anterior a la fecha actual
                        if(emptyLRes){
                            lRes= new ArrayList<>();
                            lRes.add(p);
                            emptyLRes=false;
                        }
                        else{
                            lRes.add(p);
                        }
                    }
                }
            }
        }
        return lRes;
    }
    
    @Override
    public List<Partido> listarPorFecha(){
        return (List< Partido>) (List) FachadaPersistencia.getPersistencia().buscar(Partido.class, "Partido.findByDateOrdASC", null);
    }
    
    @Override
    public List <Partido> listarProximosPartidos(EquipoPais e){
        
        List<Partido> lRes= null;
        if(e!=null){
            try{
                List<Partido> lp = listarPorFecha(e);

                if(lp!=null){
                    Date fechaActual= new Date();
                    boolean emptyLRes=true;
                    for(Partido p: lp){

                        if(p.getFecha().after(fechaActual)){//Si la fecha del partido es anterior a la fecha actual
                            if(emptyLRes){
                                lRes= new ArrayList<>();
                                lRes.add(p);
                                emptyLRes=false;
                            }
                            else{
                                lRes.add(p);
                            }
                        }
                    }
                }

            } catch (LogicaException ex) {
                Logger.getLogger(PartidosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{//Si recibe null lista todos los próximos partidos
            List  <Partido> lAux = listarPorFecha();
            
            if(lAux!=null){
                    Date fechaActual= new Date();
                    boolean emptyLRes=true;
                    for(Partido p: lAux){

                        if(p.getFecha().after(fechaActual)){//Si la fecha del partido es anterior a la fecha actual
                            if(emptyLRes){
                                lRes= new ArrayList<>();
                                lRes.add(p);
                                emptyLRes=false;
                            }
                            else{
                                lRes.add(p);
                            }
                        }
                    }
                }
            
        }
        return lRes;
    }
    
    @Override
    public List< Partido> buscarPartidosDeGrupo(DtTipo grupo) throws LogicaException {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("tipo", grupo);

        return (List< Partido>) (List) FachadaPersistencia.getPersistencia().buscar(Partido.class, "Partido.findByTipo", parametros);
    }
    
    @Override
    public DtTipo obtenerGrupoEquipo(String equipo){
        List <Partido> listP= listarPorFecha();
        if(listP!=null){
            Date fActual= new Date();//Fecha actual que se utilizara para saber si el partido ya ha sido jugado
            for(Partido p: listP){
                if(p.getEquipo_1().getNombrePais().equals(equipo)||p.getEquipo_2().getNombrePais().equals(equipo)){//Si el equipo buscado es uno de los equipos involucrados 
                    if(p.getFecha().after(fActual)){//si el partido es proximo a la fecha actual
                        return p.getTipo();
                    }
                }
            }
        }
        return null;
    }
    
    
    @Override
    public void ingresarResultado(int idPartido, int golesE1, int golesE2){
        Partido p=(Partido) FachadaPersistencia.getPersistencia().buscar(Partido.class, idPartido);
        if(p!=null){
            System.err.println("Partido "+p.getId()+" encontrado!");
            p.setGoles_1(golesE1);
            p.setGoles_2(golesE2);
            FachadaPersistencia.getPersistencia().modificar(p);
        }
    }
    
    public List<DtPosicion> getTablaGrupo(DtTipo grupo){
            List<DtPosicion> lRes=null;
        try {
            List<Partido> partidosGrupo=buscarPartidosDeGrupo(grupo);
            if(partidosGrupo!=null){
                Map<String, DtPosicion> auxMap= new HashMap<>();
                for(Partido p: partidosGrupo){
                    if(p.getGoles_1()>p.getGoles_2()){//Si ganó equipo 1
                        //if(!auxMap.isEmpty()){
                            //Inicio Registro pts Equipo 1
                            if(auxMap.get(p.getEquipo_1().getNombrePais())==null){//Si es la primera vez que encuentro un partido de equipo 1
                                DtPosicion dtp= new DtPosicion(3, 1, 1,0, 0,p.getGoles_1(), p.getGoles_2(), p.getEquipo_1());
                                auxMap.put(p.getEquipo_1().getNombrePais(), dtp);
                            }
                            else{//si ya existe el equipo en el auxMap
                                DtPosicion aux = auxMap.get(p.getEquipo_1().getNombrePais());
                                aux.setPts(aux.getPts()+3);
                                aux.setGolesFavor(aux.getGolesFavor()+p.getGoles_1());
                                aux.setGolesContra(aux.getGolesContra()+p.getGoles_2());
                                aux.setPartidosGanados(aux.getPartidosGanados()+1);
                                aux.setPartidosJugados(aux.getPartidosJugados()+1);
                                auxMap.computeIfPresent(p.getEquipo_1().getNombrePais(), (k,v) ->v=aux);
                                
                            }
                            //Inicio Registro pts Equipo 2
                            if(auxMap.get(p.getEquipo_2().getNombrePais())==null){//Si es la primera vez que encuentro un partido de equipo 1
                                DtPosicion dtp= new DtPosicion(0, 1, 0, 0, 1, p.getGoles_2(), p.getGoles_1(), p.getEquipo_2());
                                auxMap.put(p.getEquipo_2().getNombrePais(), dtp);
                            }
                            else{//si ya existe el equipo en el auxMap
                                DtPosicion aux = auxMap.get(p.getEquipo_2().getNombrePais());
                                aux.setGolesFavor(aux.getGolesFavor()+p.getGoles_2());
                                aux.setGolesContra(aux.getGolesContra()+p.getGoles_1());
                                aux.setPartidosJugados(aux.getPartidosJugados()+1);
                                aux.setPartidosPerdidos(aux.getPartidosPerdidos()+1);
                                auxMap.computeIfPresent(p.getEquipo_2().getNombrePais(), (k,v) ->v=aux);
                                
                            }
                        //}
                    }//Fin Si Ganó Equipo 1
                    else if(p.getGoles_1()<p.getGoles_2()){//Si ganó Equipo2
                        //if(!auxMap.isEmpty()){
                            //Inicio Registro pts Equipo 1
                            if(auxMap.get(p.getEquipo_1().getNombrePais())==null){//Si es la primera vez que encuentro un partido de equipo 1
                                DtPosicion dtp= new DtPosicion(0, 1, 0, 0, 1, p.getGoles_1(), p.getGoles_2(), p.getEquipo_1());
                                auxMap.put(p.getEquipo_1().getNombrePais(), dtp);
                            }
                            else{//si ya existe el equipo en el auxMap
                                DtPosicion aux = auxMap.get(p.getEquipo_1().getNombrePais());
                                
                                aux.setGolesFavor(aux.getGolesFavor()+p.getGoles_1());
                                aux.setGolesContra(aux.getGolesContra()+p.getGoles_2());
                                aux.setPartidosJugados(aux.getPartidosJugados()+1);
                                aux.setPartidosPerdidos(aux.getPartidosPerdidos()+1);
                                auxMap.computeIfPresent(p.getEquipo_1().getNombrePais(), (k,v) ->v=aux);
                                
                            }
                            //Inicio Registro pts Equipo 2
                            if(auxMap.get(p.getEquipo_2().getNombrePais())==null){//Si es la primera vez que encuentro un partido de equipo 1
                                DtPosicion dtp= new DtPosicion(3, 1, 1, 0, 0, p.getGoles_2(), p.getGoles_1(), p.getEquipo_2());
                                auxMap.put(p.getEquipo_2().getNombrePais(), dtp);
                            }
                            else{//si ya existe el equipo en el auxMap
                                DtPosicion aux = auxMap.get(p.getEquipo_2().getNombrePais());
                                aux.setPts(aux.getPts()+3);
                                aux.setGolesFavor(aux.getGolesFavor()+p.getGoles_2());
                                aux.setGolesContra(aux.getGolesContra()+p.getGoles_1());
                                aux.setPartidosGanados(aux.getPartidosGanados()+1);
                                aux.setPartidosJugados(aux.getPartidosJugados()+1);
                                auxMap.computeIfPresent(p.getEquipo_2().getNombrePais(), (k,v) ->v=aux);
                                
                            }
                        //}
                    }//Fin Si Ganó Equipo 2
                    else if(p.getGoles_1()==p.getGoles_2()){//Si empataron
                        if(p.getFecha().before(new Date())){//Si realmente se llegó a jugar el partido
                            //Inicio Registro pts Equipo 1
                            if(auxMap.get(p.getEquipo_1().getNombrePais())==null){//Si es la primera vez que encuentro un partido de equipo 1
                                DtPosicion dtp= new DtPosicion(1, 1, 0, 1, 0, p.getGoles_1(), p.getGoles_2(), p.getEquipo_1());
                                auxMap.put(p.getEquipo_1().getNombrePais(), dtp);
                            }
                            else{//si ya existe el equipo en el auxMap
                                DtPosicion aux = auxMap.get(p.getEquipo_1().getNombrePais());
                                aux.setPts(aux.getPts()+1);
                                aux.setGolesFavor(aux.getGolesFavor()+p.getGoles_1());
                                aux.setGolesContra(aux.getGolesContra()+p.getGoles_2());
                                aux.setPartidosJugados(aux.getPartidosJugados()+1);
                                aux.setPartidosEmpatados(aux.getPartidosEmpatados()+1);
                                auxMap.computeIfPresent(p.getEquipo_1().getNombrePais(), (k,v) ->v=aux);
                                
                            }
                            //Inicio Registro pts Equipo 2
                            if(auxMap.get(p.getEquipo_2().getNombrePais())==null){//Si es la primera vez que encuentro un partido de equipo 1
                                DtPosicion dtp= new DtPosicion(1, 1, 0, 1, 0, p.getGoles_2(), p.getGoles_1(), p.getEquipo_2());
                                auxMap.put(p.getEquipo_2().getNombrePais(), dtp);
                            }
                            else{//si ya existe el equipo en el auxMap
                                DtPosicion aux = auxMap.get(p.getEquipo_2().getNombrePais());
                                aux.setPts(aux.getPts()+1);
                                aux.setGolesFavor(aux.getGolesFavor()+p.getGoles_2());
                                aux.setGolesContra(aux.getGolesContra()+p.getGoles_1());
                                aux.setPartidosJugados(aux.getPartidosJugados()+1);
                                aux.setPartidosEmpatados(aux.getPartidosEmpatados()+1);
                                auxMap.computeIfPresent(p.getEquipo_2().getNombrePais(), (k,v) ->v=aux);
                                
                            }
                        }
                        else{//Si aún no se jugó el partido
                            //Inicio Registro pts Equipo 1
                            if(auxMap.get(p.getEquipo_1().getNombrePais())==null){//Si es la primera vez que encuentro un partido de equipo 1
                                DtPosicion dtp= new DtPosicion(0, 0, 0, 0, 0, 0, 0, p.getEquipo_1());
                                auxMap.put(p.getEquipo_1().getNombrePais(), dtp);
                            }
                            //Inicio Registro pts Equipo 2
                            if(auxMap.get(p.getEquipo_2().getNombrePais())==null){//Si es la primera vez que encuentro un partido de equipo 1
                                DtPosicion dtp= new DtPosicion(0, 0, 0, 0, 0, 0, 0, p.getEquipo_2());
                                auxMap.put(p.getEquipo_2().getNombrePais(), dtp);
                            }
                        }
                    }//Fin Si Hubo Empate
                }
                Iterator it=auxMap.keySet().iterator();
                lRes=new ArrayList<>();
                while(it.hasNext()){
                    String key=(String) it.next();
                    lRes.add(auxMap.get(key));
                }
                //------Ordeno por Puntaje----
                Collections.sort(lRes, new Comparator<DtPosicion>() {
				@Override
				public int compare(DtPosicion lhs, DtPosicion rhs) {
				    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
			    return lhs.getPts()> rhs.getPts()? -1 
			    	: lhs.getPts()< rhs.getPts()? 1 : 0;
			    }
			});
                //----------------------------
                for(DtPosicion p: lRes){
                    System.out.println("Equipo: "+p.getEquipo().getNombrePais() +"   Pts:"+p.getPts());
                }
                return lRes;
            }
            
        } catch (LogicaException ex) {
            Logger.getLogger(PartidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lRes;
    }

}
