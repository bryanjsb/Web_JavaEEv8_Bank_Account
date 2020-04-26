package controlador.Cajero;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cuenta.DaoCuenta;
import modelo.cuenta.cuenta;

@WebServlet(name = "controllerCajeroAcredInteres", urlPatterns = {"/controllerCajeroAcredInteres"})
public class controllerCajeroAcredInteres extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

         HttpSession sesion = request.getSession(true);
         if (request.getServletPath().equals("/controllerCajeroAcredInteres")) {

            paginaAcreditacion(request, response,sesion);
        }
    }

    
    private void paginaAcreditacion(HttpServletRequest request, HttpServletResponse response , HttpSession sesion)
            throws ServletException, IOException {
        
        cuenta c=new cuenta();
        sesion.setAttribute("listaCuentaPositivo", c);
        
        response.sendRedirect("vista/cajero/acreditacion/acreditacion.jsp");
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
