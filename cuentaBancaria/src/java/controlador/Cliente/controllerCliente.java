package controlador.Cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cliente.Cliente;
import modelo.movimiento.movimiento;
import modelo.usuario.Usuario;

@WebServlet(name = "controllerCliente",
        urlPatterns = {"/controllerCliente",
            "/enviarMovConsulta",
            "/regresarConsultaCuenta"
        })
public class controllerCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession(true);
        if (request.getServletPath().equals("/controllerCliente")) {
            this.paginaPrincipalCliente(request, response, sesion);
        }

        if (request.getServletPath().equals("/enviarMovConsulta")) {
            this.paginaMovimientoCliente(request, response, sesion);
        }

        if (request.getServletPath().equals("/regresarConsultaCuenta")) {
            this.paginaConsultaCuenta(request, response, sesion);
        }
    }

    private void paginaPrincipalCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        Cliente cliente = Cliente.obtenerCliente(usuario.getIdUsuario());

        sesion.setAttribute("cliente", cliente);
        response.sendRedirect("vista/cliente/cliente.jsp");

    }

    private void paginaMovimientoCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        Cliente cliente = Cliente.obtenerCliente(usuario.getIdUsuario());

        sesion.setAttribute("cliente", cliente);

        movimiento mov = new movimiento();

        mov.setNum_cuenta(request.getParameter("ClienteMov"));

        sesion.setAttribute("movimiento", mov);
        response.sendRedirect("vista/cliente/consultas/consultaMovimientos.jsp");

    }

    private void paginaConsultaCuenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {
        response.sendRedirect("vista/cliente/consultas/consulta_cuenta.jsp");
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
