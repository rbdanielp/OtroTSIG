/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.datatypes;

import uy.tsig.grupo12.entidades.Partidos.EquipoPais;

/**
 *
 * @author tecnoinf
 */
public class DtPosicion {
    private int pts;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesFavor;
    private int golesContra;
    private EquipoPais equipo;

    public DtPosicion(int pts, int partidosJugados, int partidosGanados, int partidosEmpatados, int partidosPerdidos, int golesFavor, int golesContra, EquipoPais equipo) {
        this.pts=pts;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosEmpatados=partidosEmpatados;
        this.partidosPerdidos=partidosPerdidos;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
        this.equipo = equipo;
    }

    public int getPts() {
        return pts;
    }

    
    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public EquipoPais getEquipo() {
        return equipo;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public void setEquipo(EquipoPais equipo) {
        this.equipo = equipo;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }
    
    
    
}
