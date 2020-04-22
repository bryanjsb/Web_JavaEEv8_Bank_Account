package controlador.Cajero;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cliente.DaoCliente;
import modelo.cuenta.DaoCuenta;
import modelo.cuenta.cuenta;

@WebServlet(name = "controllerCajeroDeposito",
        urlPatterns = {"/controllerCajeroDeposito",
            "/buscarClienteDeposito",
            "/vista/cajero/Deposito"

        })
public class controllerCajeroDeposito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession(true);
        if (request.getServletPath().equals("/vista/cajero/Deposito")) {
            paginaDeposito(request, response);

        }

        if (request.getServletPath().equals("/buscarClienteDeposito")) {

            comprobarTipoBusqueda(request, response, sesion);

        }

        if (request.getServletPath().equals(" ")) {

        }

        if (request.getServletPath().equals(" ")) {

        }

        if (request.getServletPath().equals(" ")) {

        }
    }

    private void comprobarTipoBusqueda(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String tipoBusqueda = request.getParameter("busquedaDeposito");
        String codigoIngresado = request.getParameter("buscarDepostio");
        DaoCuenta daoCuenta = DaoCuenta.obtenerInstancia();
        DaoCliente daoCliente = DaoCliente.obtenerInstancia();
        if (tipoBusqueda != null && codigoIngresado != null) {
            if (tipoBusqueda.equals("numCuenta") && daoCuenta.verificarCuenta(codigoIngresado)) {

                cuenta cuenta = daoCuenta.obtenerNumeroCuenta(codigoIngresado).get();
                sesion.setAttribute("cuenta", cuenta);
                response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/depositoNumeroCuenta.jsp");
            } else if (tipoBusqueda.equals("idUsuario") && daoCliente.verificarCliente(codigoIngresado)) {

                List<cuenta> listaCuentasCliente = daoCuenta.obtenerListaCuentaCliente(codigoIngresado);
                sesion.setAttribute("listaCuentasCliente", listaCuentasCliente);
                 response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/depositoIdCliente.jsp");
            }
        }

    }

    private void paginaDeposito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/Deposito.jsp");

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
