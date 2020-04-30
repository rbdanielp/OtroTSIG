/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.logica.interfaces;

import java.util.List;
import uy.tsig.grupo12.datatypes.DtPosicion;
import uy.tsig.grupo12.entidades.Partidos.DtTipo;

import uy.tsig.grupo12.entidades.Partidos.EquipoPais;
import uy.tsig.grupo12.entidades.Partidos.Partido;
import uy.tsig.grupo12.logica.LogicaException;

/**
 *
 * @author tecnoinf
 */
public interface IPartidos {

    List< Partido> buscar(EquipoPais e) throws LogicaException;

    List< Partido> listar() throws LogicaException;
    
    List< EquipoPais>listarEquipos() throws LogicaException;

    void alta(Partido p) throws LogicaException;

    void alta(EquipoPais e) throws LogicaException;

    void modificar(Partido p) throws LogicaException;
    
    List<Partido> listarPorFecha(EquipoPais e) throws LogicaException;

    //Listar los partidos anteriores a la fecha actual
    List<Partido> listarPartidosJugados(EquipoPais e);
    
    //Lista ordenada por fecha de todos los partidos en el sistema
    List<Partido> listarPorFecha();
    
    //Listar Proximos Partidos de un equipo 'e' Ordenados por fecha
    List <Partido> listarProximosPartidos(EquipoPais e);
    
    //Listar todos los partidos de un grupo/tipo
    List< Partido> buscarPartidosDeGrupo(DtTipo grupo) throws LogicaException;
    
    //Obtener el grupo al que pertenece un Equipo
    DtTipo obtenerGrupoEquipo(String equipo);
    //Lista con equipos y sus respectivos puntajes del grupo g
    List<DtPosicion> getTablaGrupo(DtTipo grupo);
     
    void ingresarResultado(int idPartido, int golesE1, int golesE2);
    
}
