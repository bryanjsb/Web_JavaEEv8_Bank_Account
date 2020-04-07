/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Cliente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cliente.Cliente;
import modelo.cliente.DaoCliente;
import modelo.usuario.DaoUsuario;
import modelo.usuario.Usuario;

/**
 *
 * @author Bryan
 */
@WebServlet(name = "controllerCliente",
        urlPatterns = {"/controllerCliente", "/ClienteBuscar", "/registarNuevoUsuario"})
public class controllerCliente extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        if (request.getServletPath().equals("/controllerCliente")) {
            this.paginaPrincipalCliente(request, response);
        }
        
        if (request.getServletPath().equals("/ClienteBuscar")) {
            this.buscarCliente(request.getParameter("buscarCliente"), request, response);
        }

        if (request.getServletPath().equals("/registarNuevoUsuario")) {
            this.registrarCliente(request, response);
        }

//            sesion.setAttribute("usuario", tipoUsuario.get());
    }

    private void paginaPrincipalCliente( HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    response.sendRedirect("vista/cliente/cliente.jsp");
    
    }
    private void buscarCliente(String id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = new Cliente();
        String destino = null;
        HttpSession sesion = request.getSession(true);
        DaoCliente dc=DaoCliente.obtenerInstancia();
        if (dc.verificarCliente(id)) {

            sesion.setAttribute("cliente",  dc.obtenerCliente(id));
            sesion.setAttribute("idBusqueda", dc.obtenerCliente(id).get().getIdCliente());
            destino = "controllerCajero";
        } else {

            sesion.setAttribute("idBusqueda", id);
            destino = "vista/cajero/registrarCliente";
        }
        response.sendRedirect(destino);

    }

    private void registrarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        Cliente clienteNuevo= new Cliente(
                request.getParameter("login"),
                request.getParameter("login"),
                request.getParameter("apellidos"),
                request.getParameter("nombre"),
                request.getParameter("telefono")
        );

        Usuario usuario = new Usuario(
                request.getParameter("login"),
                request.getParameter("clave"), 0, 1
        );
        
        DaoCliente dc=DaoCliente.obtenerInstancia();
        DaoUsuario du=DaoUsuario.obtenerInstancia();
        if (dc.registrarCliente(clienteNuevo)&& du.registrarUsuario(usuario)) {

            response.sendRedirect("controllerCajero");
        } else {
            //error
        }

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
