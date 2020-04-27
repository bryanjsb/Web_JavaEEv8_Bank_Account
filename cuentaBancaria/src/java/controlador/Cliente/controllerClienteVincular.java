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
import modelo.cuenta.cuenta;
import modelo.favorita.favorita;

@WebServlet(name = "controllerClienteVincular",
        urlPatterns = {"/controllerClienteVincular",
            "/buscarClienteFavorito", "/realizarFavoritoID", "/confirmarVinculacion"

        })
public class controllerClienteVincular extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession(true);

        if (request.getServletPath().equals("/controllerClienteVincular")) {

            paginaVincular(request, response);

        }
        if (request.getServletPath().equals("/buscarClienteFavorito")) {

            comprobarTipoBusqueda(request, response, sesion);

        }

        if (request.getServletPath().equals("/realizarFavoritoID")) {

            verificarRetiroPorID(request, response, sesion);

        }

        if (request.getServletPath().equals("/confirmarVinculacion")) {

            verificarVincularCuenta(request, response, sesion);

        }

    }

    private void paginaVincular(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/cuentaBancaria/vista/cliente/vincularCuenta/vincularCuentas.jsp");

    }

    private void comprobarTipoBusqueda(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String tipoBusqueda = request.getParameter("busquedaFavorito");
        String codigoIngresado = request.getParameter("buscarFavorito");

        if (tipoBusqueda != null && codigoIngresado != null) {

            // comprueba si la busqueda es por numero de cuenta
            if (tipoBusqueda.equals("numCuenta") && cuenta.verificarCuenta(codigoIngresado)) {

                cuenta cuentaCliente = cuenta.obtenerCuenta(codigoIngresado);
                sesion.setAttribute("cuenta", cuentaCliente);

//                Cliente cliente=(Cliente) sesion.getAttribute("cliente");
//            favorita favorita=new favorita(cliente.getIdCliente(),codigoIngresado);
//            
//            favorita.agregarCuentaFavorita(favorita);
                response.sendRedirect("/cuentaBancaria/vista/cliente/vincularCuenta/confirmarVinculacion.jsp");

            } else // comprueba si la busqueda es por id usuario
            if (tipoBusqueda.equals("idUsuario")
                    && Cliente.verificarCliente(codigoIngresado)) {

                sesion.setAttribute("listaCuentasFavorita", Cliente.obtenerCliente(codigoIngresado));
                response.sendRedirect("/cuentaBancaria/vista/cliente/vincularCuenta/seleccionCuentaVincular.jsp");

            }
        } else {
            // recursivo hasta que ingrese bien los datos
            paginaVincular(request, response);

        }

    }

    private void verificarRetiroPorID(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String numCuenta = request.getParameter("numCuentaCliente");

        if (cuenta.verificarCuenta(numCuenta)) {

            Cliente cliente = (Cliente) sesion.getAttribute("cliente");

            favorita favorita = new favorita(cliente.getIdCliente(), numCuenta);

            favorita.agregarCuentaFavorita(favorita);

            response.sendRedirect("controllerCliente");
        } else {

            response.sendRedirect("controllerClienteVincular");
        }

    }

    private void verificarVincularCuenta(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        cuenta cuenta = (cuenta) sesion.getAttribute("cuenta");
        Cliente cliente = (Cliente) sesion.getAttribute("cliente");

        if (cuenta != null) {

            favorita favorita = new favorita(cliente.getIdCliente(), cuenta.getNum_cuenta());

            favorita.agregarCuentaFavorita(favorita);

            response.sendRedirect("controllerCliente");
        } else {

            response.sendRedirect("controllerClienteVincular");
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
