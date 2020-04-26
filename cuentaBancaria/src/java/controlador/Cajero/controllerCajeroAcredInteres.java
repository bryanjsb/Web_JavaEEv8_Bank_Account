package controlador.Cajero;

import modelo.acreditacion.acreditacion;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cuenta.DaoCuenta;
import modelo.cuenta.cuenta;
import modelo.movimiento.movimiento;

@WebServlet(name = "controllerCajeroAcredInteres",
        urlPatterns = {"/controllerCajeroAcredInteres",
            "/aplicarAcreditacion",
            "/confirmarAcreditacion"
        })
public class controllerCajeroAcredInteres extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession(true);

        acreditacion c = new acreditacion();
        sesion.setAttribute("acreditacion", c);

        if (request.getServletPath().equals("/controllerCajeroAcredInteres")) {

            paginaAcreditacion(request, response, sesion);
        }

        if (request.getServletPath().equals("/aplicarAcreditacion")) {

            this.confirmarAcreditacion(request, response, sesion);
        }

        if (request.getServletPath().equals("/confirmarAcreditacion")) {

            aplicarAcreditacion(request, response, sesion);
        }
    }

    private void paginaAcreditacion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        response.sendRedirect("vista/cajero/acreditacion/acreditacion.jsp");
    }

    private void confirmarAcreditacion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        response.sendRedirect("vista/cajero/acreditacion/acreditacionConfirmar.jsp");

    }

    private void aplicarAcreditacion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        acreditacion c = (acreditacion) sesion.getAttribute("acreditacion");
        c.hacerDeposito();
        response.sendRedirect("controllerCajero");
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
