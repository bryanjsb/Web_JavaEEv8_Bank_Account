/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.DaoUsuario;
import modelo.usuario.Usuario;

/**
 *
 * @author Bryan
 */
@WebServlet(name = "controllerLogin", urlPatterns = {"/controllerLogin"})
public class controllerLogin extends HttpServlet {

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
        
         String usuario= request.getParameter("usuario");
         String clave= request.getParameter("password");
         boolean usuarioValido=false;
         DaoUsuario daoUsuario= DaoUsuario.obtenerInstancia();
         if(usuario!=null && clave!=null){
             usuarioValido=daoUsuario.verificarUsuario(usuario, clave);
         }
       
         if(usuarioValido){
//             HttpSession session=request.getSession(true);  
             
             Optional<Usuario>tipoUsuario=daoUsuario.obtenerUsuario(usuario);
             
//             session.setAttribute("usuarioLogin",tipoUsuario);  
//              session.setMaxInactiveInterval(60 * 3);
              
              
              //request.getRequestDispatcher("principal.jsp").forward(
            //        request, response);
            String redirecion=redireccionarUsuario(tipoUsuario);
//            response.sendRedirect(redirecion);
             request.getRequestDispatcher(redirecion).forward(
                    request, response);
         }

    }

    
    private String redireccionarUsuario(Optional<Usuario> usuario){
        return(usuario.get().getRol()==0)?"/vista/cajero/cajero.jsp":
                "/vista/cliente/cliente.jsp";
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
