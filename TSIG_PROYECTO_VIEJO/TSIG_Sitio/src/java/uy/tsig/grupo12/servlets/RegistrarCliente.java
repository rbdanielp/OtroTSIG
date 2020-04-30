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
import javax.servlet.http.HttpSession;
//import org.apache.jasper.tagplugins.jstl.core.ForEach;
import uy.tsig.grupo12.Enums.Estado_Sesion;
import uy.tsig.grupo12.entidades.Partidos.EquipoPais;
import uy.tsig.grupo12.entidades.Usuario.Cliente;
import uy.tsig.grupo12.logica.FachadaLogica;
import uy.tsig.grupo12.logica.LogicaException;

/**
 *
 * @author tecnoinf
 */
public class RegistrarCliente extends HttpServlet {

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
        request.getRequestDispatcher("/WEB-INF/Auth/RegistrarCliente.jsp").forward(request, response);
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

        try {
            List <EquipoPais> equipos = FachadaLogica.getPartidoController().listarEquipos();
            List<String> paises = null;
            if(equipos!=null){
                 paises = new ArrayList<>();
                for (EquipoPais ep: equipos){
                    System.out.println(ep.getNombrePais());
                    paises.add(ep.getNombrePais());
                }
            }
            request.setAttribute("paises", paises);
            request.getRequestDispatcher("/WEB-INF/Auth/RegistrarCliente.jsp").forward(request, response);
        } catch (LogicaException ex) {
            Logger.getLogger(RegistrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

            
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
        String idUsuario = request.getParameter("idUsr");
        String nombreU = request.getParameter("nombre");
        String password = request.getParameter("password");
        String nacionalidad = request.getParameter("pais");
        
        EquipoPais pais = new EquipoPais(nacionalidad);
        Cliente c = new Cliente(idUsuario, password , nombreU, pais, null);
        try {
            FachadaLogica.getUsuarioController().alta(c);
            //Se le concede la sesion
            HttpSession sesion = request.getSession();
            Estado_Sesion estadoS;
            estadoS = Estado_Sesion.ACTIVA;
            sesion.setAttribute("nickUsr", c.getLoginUser());
            sesion.setAttribute("estado_Sesion", estadoS);
            request.setAttribute("equipoUsr", c.getNacionalidad().getNombrePais());

            request.getRequestDispatcher("//index.jsp").forward(request, response);


        } catch (LogicaException ex) {
            Logger.getLogger(RegistrarCliente.class.getName()).log(Level.SEVERE, null, ex);
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
