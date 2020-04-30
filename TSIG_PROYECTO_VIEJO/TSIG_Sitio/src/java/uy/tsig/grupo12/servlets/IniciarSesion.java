/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uy.tsig.grupo12.Enums.Estado_Sesion;
import uy.tsig.grupo12.datatypes.DtPosicion;
import uy.tsig.grupo12.entidades.Partidos.Partido;
import uy.tsig.grupo12.entidades.Usuario.Cliente;
import uy.tsig.grupo12.entidades.Usuario.Usuario;
import uy.tsig.grupo12.logica.FachadaLogica;

/**
 *
 * @author tecnoinf
 */
public class IniciarSesion extends HttpServlet {

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
        HttpSession sesion = request.getSession();
        String usuario = request.getParameter("idUsuario");
        String password = request.getParameter("password");
        Estado_Sesion estadoS;
        
        Usuario u;
        try {
            if (usuario != null) {
                u = FachadaLogica.getUsuarioController().login(usuario, password);
                
                if(u!=null){
                    
                    try{
                        estadoS = Estado_Sesion.ACTIVA;
                        sesion.setAttribute("nickUsr", u.getLoginUser());
                        sesion.setAttribute("estado_Sesion", estadoS);
                        Cliente c = (Cliente) u;//Verifico si es un Cliente
                        sesion.setAttribute("equipoUsr", c.getNacionalidad().getNombrePais());
                        List <Partido> ultimosPartidos = FachadaLogica.getPartidoController().listarPartidosJugados(c.getNacionalidad());
                        sesion.setAttribute("ultimosPartidos", ultimosPartidos);
                        List<DtPosicion> tablaGrp=FachadaLogica.getPartidoController().getTablaGrupo(ultimosPartidos.get(ultimosPartidos.size()-1).getTipo());
                        sesion.setAttribute("tablaGrupo", tablaGrp);
                    }catch (Exception e){// Es administrador
                        sesion.setAttribute("admin", true);
                        List <Partido> ultimosPartidos = FachadaLogica.getPartidoController().listarPorFecha(null);
                        sesion.setAttribute("ultimosPartidos", ultimosPartidos);
                        e.printStackTrace();
                    }
                    request.getRequestDispatcher("/Home").forward(request, response);
                }
                else{
                    estadoS = Estado_Sesion.NO_ACTIVA;//Usuario o contraseña no validos
                    sesion.setAttribute("estado_Sesion", estadoS);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/Auth/Login.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
