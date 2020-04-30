/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uy.tsig.grupo12.entidades.POI.Estadio;
import uy.tsig.grupo12.entidades.Partidos.DtTipo;
import uy.tsig.grupo12.entidades.Partidos.EquipoPais;
import uy.tsig.grupo12.entidades.Partidos.Partido;
import uy.tsig.grupo12.logica.FachadaLogica;
import uy.tsig.grupo12.logica.LogicaException;

/**
 *
 * @author tecnoinf
 */
public class RegInfoTorneos extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
                request.getRequestDispatcher("/WEB-INF/Torneo/RegInfoTorneo.jsp").forward(request,response);
    }*/

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List <EquipoPais> equipos = FachadaLogica.getPartidoController().listarEquipos();
            List<String> paises = null;
            List<DtTipo> grupos = new ArrayList<>();
            grupos.add(DtTipo.GRUPO_A);grupos.add(DtTipo.GRUPO_B);grupos.add(DtTipo.GRUPO_C);grupos.add(DtTipo.GRUPO_D);grupos.add(DtTipo.GRUPO_E);
            grupos.add(DtTipo.GRUPO_F);grupos.add(DtTipo.GRUPO_G);grupos.add(DtTipo.GRUPO_H);grupos.add(DtTipo.OCTAVOS);grupos.add(DtTipo.CUARTOS);
            grupos.add(DtTipo.SEMIFINAL);grupos.add(DtTipo.TYC_PUESTO);grupos.add(DtTipo.FINAL);
            List<Estadio> estadios = FachadaLogica.getPOIController().listarEstadios();
            List<Partido> partidosFinalizados=FachadaLogica.getPartidoController().listarPartidosJugados(null);
            if(equipos!=null){
                 paises = new ArrayList<>();
                for (EquipoPais ep: equipos){
                    System.out.println(ep.getNombrePais());
                    paises.add(ep.getNombrePais());
                }
            }
            request.setAttribute("equipos", paises);
            request.setAttribute("grupos", grupos);
            request.setAttribute("estadios", estadios);
            request.setAttribute("partidosJugados", partidosFinalizados);
            request.getRequestDispatcher("/WEB-INF/Torneo/RegInfoTorneo.jsp").forward(request, response);
        } catch (LogicaException ex) {
            Logger.getLogger(RegInfoTorneos.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //---------------------------------------------
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
