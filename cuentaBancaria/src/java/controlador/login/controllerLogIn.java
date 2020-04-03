/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.login;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.ServicioUsuario;
import modelo.usuario.Usuario;

/**
 *
 * @author Bryan
 */
@WebServlet(name = "controllerLogIn",
        urlPatterns = {"/controllerLogIn",
            "/vista/login/ServicioLogin","/ServicioLogin"})
public class controllerLogIn extends HttpServlet {

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
        
        
        if(request.getServletPath().equals("/ServicioLogin")){
        
            String us = (String) request.getParameter("usuario");       

            Optional<Usuario> usuario = servicioUsuario.obtenerUsuario("3");
            
            if("3".equals(us)){
             request.getRequestDispatcher("vista/cajero/cajeroInicio.jsp")
                            .forward(request, response);
            }
            
//            switch (usuario.get().getRol()) {
//                case 0:
//                    request.getRequestDispatcher("vista/cajero/cajeroInicio.jsp")
//                            .forward(request, response);
//                    break;
//                case 1:
//                    request.getRequestDispatcher("vista/cliente/clienteInicio.jsp")
//                            .forward(request, response);
//                    break;
//                default:
//                    break;
//            }
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

    boolean verificar(HttpServletRequest request) {
        if (request.getParameter("usuario") == null) {
            return false;
        }
        if (request.getParameter("password") == null) {
            return false;
        }
        return true;
    }

//private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (this.verificar(request)) {
//            Map<String, String> errors = this.validar(request);
//            if (errors.isEmpty()) {
//                Usuario model = new Usuario();
//                updateModel(model, request);
//                request.setAttribute("model", model);
//                //-----------------------------------------
//                Model domainModel = Model.instance();
//                HttpSession session = request.getSession(true);
//
//                Principal principal = request.getUserPrincipal();
//                if (principal != null) {
//                    try {
//                        request.logout();
//                    } catch (ServletException ex) {
//                    }
//                }
//
//                try {
//                    request.login(model.getId(), model.getClave());
//                    //principal = request.getUserPrincipal();
//
//                    Usuario logged = domainModel.getUsuarioDAO().findById(model.getId());
//                    session.setAttribute("logged", logged);
//
//                    //Seteando el funcionario actual...
//                    request.getSession().setAttribute("funcActual", this.obtenerFuncionarioActual(logged));
//                    //Seteando el rol actual...
//                    request.getSession().setAttribute("rolActual", this.obtenerRolActual(logged));
//                    //Seteando la dependencia actual...
//                    request.getSession().setAttribute("depActual", this.obtenerDependenciaActual(logged));
//
//                    //Pues todos los tipos de usuario van a llegar al Lobby del sistema...
//                    request.getRequestDispatcher("/presentation/users/Lobby").forward(request, response);
//                    
//                } catch (Exception ex) {
//                    request.getRequestDispatcher("/presentation/login/login.jsp").forward(request, response);
//                }
//            } else {
//                request.setAttribute("errors", errors);
//                request.getRequestDispatcher("/presentation/login/login.jsp").forward(request, response);
//            }
//        } else {
//            request.getRequestDispatcher("/presentation/login/login.jsp").forward(request, response);
//        }
//    }
    
    private final ServicioUsuario servicioUsuario = new ServicioUsuario();
}

