/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.BarJpaController;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.ComentarioJpaController;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.EstadioJpaController;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.HotelJpaController;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.PuntoTuristicoJpaController;
import uy.tsig.grupo12.entidades.POI.Bar;
import uy.tsig.grupo12.entidades.POI.Comentario;
import uy.tsig.grupo12.entidades.POI.Estadio;
import uy.tsig.grupo12.entidades.POI.Hotel;
import uy.tsig.grupo12.entidades.POI.POI;
import uy.tsig.grupo12.entidades.POI.PuntoTuristico;
import uy.tsig.grupo12.entidades.Usuario.Cliente;
import uy.tsig.grupo12.entidades.Usuario.Usuario;
import uy.tsig.grupo12.logica.FachadaLogica;
import uy.tsig.grupo12.logica.LogicaException;

/**
 *
 * @author diego
 */
public class RegistrarComentario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Auth/registrarComentario.jsp").forward(request, response);
    }

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
        request.getRequestDispatcher("/WEB-INF/Auth/registrarComentario.jsp").forward(request, response);
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
        request.getRequestDispatcher("/index.jsp").forward(request, response);

        EntityManagerFactory fact = Persistence.createEntityManagerFactory("TSIG_GP12PU");
        BarJpaController ctrlbar = new BarJpaController(fact);
        HotelJpaController ctrlHotel = new HotelJpaController(fact);
        EstadioJpaController ctrlEstadio = new EstadioJpaController(fact);
        PuntoTuristicoJpaController ctrlPt = new PuntoTuristicoJpaController(fact);
        ComentarioJpaController ctrlCom = new ComentarioJpaController(fact);

        double puntajePoi = 0;
        String comentario = request.getParameter("comentario");
        String puntaje = request.getParameter("puntaje");
        String pointSelect = request.getParameter("puntoId");
        String dtype = request.getParameter("dtype");
        int puntoId = Integer.parseInt(pointSelect);
        int punt = Integer.parseInt(puntaje);
        HttpSession sesion = request.getSession();
        //String usuario = sesion.request.getParameter("nickUsr");
        String usuario = request.getSession().getAttribute("nickUsr").toString();
        POI poi = null;
        switch (dtype) {

            case "Bar": {

                POI bar = new Bar();
                Comentario com = new Comentario();
                com.setComentario(comentario);
                com.setPuntaje(punt);
                try {
                    Usuario usr = FachadaLogica.getUsuarioController().buscarUsuario(usuario);
                    bar = FachadaLogica.getPOIController().buscarPOI(puntoId);
                    com.setCliente((Cliente) usr);
                    com.setPoi(poi);
                    ctrlCom.create(com);
                } catch (LogicaException ex) {
                    Logger.getLogger(RegistrarComentario.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<Comentario> comentarios = bar.getComentarios();
                comentarios.add(com);
                puntajePoi = ((bar.getPuntaje() + punt) / comentarios.size());
                bar.setPuntaje(puntajePoi);
                try {
                    ctrlbar.edit((Bar) bar);
                } catch (Exception ex) {
                    Logger.getLogger(RegistrarComentario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case "Estadio": {
                POI Estadio = new Estadio();
                Comentario com = new Comentario();
                com.setComentario(comentario);
                com.setPuntaje(punt);
                try {
                    Usuario usr = FachadaLogica.getUsuarioController().buscarUsuario(usuario);
                    Estadio = FachadaLogica.getPOIController().buscarPOI(puntoId);
                    com.setCliente((Cliente) usr);
                    com.setPoi(poi);
                    ctrlCom.create(com);
                } catch (LogicaException ex) {
                    Logger.getLogger(RegistrarComentario.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<Comentario> comentarios = Estadio.getComentarios();
                comentarios.add(com);
                puntajePoi = ((Estadio.getPuntaje() + punt) / comentarios.size());
                Estadio.setPuntaje(puntajePoi);
                try {
                    ctrlEstadio.edit((Estadio) Estadio);
                } catch (Exception ex) {
                    Logger.getLogger(RegistrarComentario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case "Hotel": {
                POI hotel = new Hotel();
                Comentario com = new Comentario();
                com.setComentario(comentario);
                com.setPuntaje(punt);
                try {
                    Usuario usr = FachadaLogica.getUsuarioController().buscarUsuario(usuario);
                    hotel = FachadaLogica.getPOIController().buscarPOI(puntoId);
                    com.setCliente((Cliente) usr);
                    com.setPoi(poi);
                    ctrlCom.create(com);
                } catch (LogicaException ex) {
                    Logger.getLogger(RegistrarComentario.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<Comentario> comentarios = hotel.getComentarios();
                comentarios.add(com);
                puntajePoi = ((hotel.getPuntaje() + punt) / comentarios.size());
                hotel.setPuntaje(puntajePoi);
                try {
                    ctrlHotel.edit((Hotel) hotel);
                } catch (Exception ex) {
                    Logger.getLogger(RegistrarComentario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case "PuntoTuristico": {
                POI PTuristico = new PuntoTuristico();
                Comentario com = new Comentario();
                com.setComentario(comentario);
                com.setPuntaje(punt);
                try {
                    Usuario usr = FachadaLogica.getUsuarioController().buscarUsuario(usuario);
                    PTuristico = FachadaLogica.getPOIController().buscarPOI(puntoId);
                    com.setCliente((Cliente) usr);
                    com.setPoi(poi);
                    ctrlCom.create(com);
                } catch (LogicaException ex) {
                    Logger.getLogger(RegistrarComentario.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<Comentario> comentarios = PTuristico.getComentarios();
                comentarios.add(com);
                puntajePoi = ((PTuristico.getPuntaje() + punt) / comentarios.size());
                PTuristico.setPuntaje(puntajePoi);
                try {
                    ctrlPt.edit((PuntoTuristico) PTuristico);
                } catch (Exception ex) {
                    Logger.getLogger(RegistrarComentario.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }
        }
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
