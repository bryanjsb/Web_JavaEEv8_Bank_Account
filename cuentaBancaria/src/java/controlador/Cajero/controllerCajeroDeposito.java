package controlador.Cajero;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cliente.Cliente;
import modelo.cuenta.cuenta;
import modelo.movimiento.movimiento;

@WebServlet(name = "controllerCajeroDeposito",
        urlPatterns = {"/controllerCajeroDeposito",
            "/buscarClienteDeposito",
            "/vista/cajero/Deposito",
            "/realizarDeposito",
            "/confirmarDeposito",
            "/realizarDepositoID"
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

        if (request.getServletPath().equals("/realizarDeposito")) {
            verificarDepositoPorNumCuenta(request, response, sesion);
        }

        if (request.getServletPath().equals("/confirmarDeposito")) {
            hacerDeposito(request, response, sesion);
        }

        if (request.getServletPath().equals("/realizarDepositoID")) {
            verificarDepositoPorID(request, response, sesion);
        }
    }

    private void comprobarTipoBusqueda(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String tipoBusqueda = request.getParameter("busquedaDeposito");
        String codigoIngresado = request.getParameter("buscarDepostio");

        if (tipoBusqueda != null && codigoIngresado != null) {

            // comprueba si la busqueda es por numero de cuenta
            if (tipoBusqueda.equals("numCuenta") && cuenta.verificarCuenta(codigoIngresado)) {

                cuenta cuentaCliente = cuenta.obtenerCuenta(codigoIngresado);
                sesion.setAttribute("cuenta", cuentaCliente);

                response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/depositoNumeroCuenta.jsp");

            } else // comprueba si la busqueda es por id usuario
            if (tipoBusqueda.equals("idUsuario")
                    && Cliente.verificarCliente(codigoIngresado)) {

                sesion.setAttribute("listaCuentasCliente", Cliente.obtenerCliente(codigoIngresado));
                response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/depositoIdCliente.jsp");

            }
        } else {
            // recursivo hasta que ingrese bien los datos
            paginaDeposito(request, response);

        }

    }

    private void paginaDeposito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/Deposito.jsp");

    }

    private void verificarDepositoPorID(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String numCuenta = request.getParameter("numCuentaCliente");

        if (cuenta.verificarCuenta(numCuenta)) {
            String verificarId = request.getParameter("verificarId");

            Double montoDeposito = Double.parseDouble(request.getParameter("montoDeposito"));
            cuenta ptrCuenta = cuenta.obtenerCuenta(numCuenta);
            String motivoDeposito = (request.getParameter("motivoDeposito"));

            Cliente ptrCliente = Cliente.obtenerCliente(verificarId);

            int aplicado = 1;
            if ((montoDeposito < 0)) {
                aplicado = 0;
                response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/depositoIdCliente.jsp");

            } else if (ptrCuenta.getCliente_id_cliente().equals(verificarId)) {
                // si entra es por que es su cuenta 
            } else {
                // es por que  usuario  deposita a otra cuenta de otro cliente

                motivoDeposito += "\\  nom:" + ptrCliente.nombreCompleto();

            }
            ptrCuenta.realizarDeposito(montoDeposito);

            movimiento ptrMovim = new movimiento(movimiento.generarIdMovimientoUnico(),
                    ptrCuenta.getNum_cuenta(), montoDeposito,
                    servicios.ServicioFecha.fechaActualCuenta(), aplicado, motivoDeposito);

            sesion.setAttribute("ptrCuenta", ptrCuenta);
            sesion.setAttribute("ptrMovim", ptrMovim);
            sesion.setAttribute("ptrCliente", ptrCliente);
            response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/confirmarDeposito.jsp");

        } else {
            response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/depositoIdCliente.jsp");
        }

    }

    private void verificarDepositoPorNumCuenta(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String verificarId = request.getParameter("verificarId");

        Double montoDeposito = Double.parseDouble(request.getParameter("montoDeposito"));

        cuenta ptrCuenta = (cuenta) sesion.getAttribute("cuenta");
        String motivoDeposito = (request.getParameter("motivoDeposito"));

        Cliente ptrCliente = Cliente.obtenerCliente(verificarId);

        int aplicado = 1;
        if (ptrCuenta.getActiva() == 1 && ptrCuenta != null) {
            if ((montoDeposito < 0)) {
                aplicado = 0;
                response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/Deposito.jsp");

            } else if (ptrCuenta.getCliente_id_cliente().equals(verificarId)) {
                // si entra es por que es su cuenta 
            } else {
                // es por que  usuario  deposita a otra cuenta de otro cliente

                motivoDeposito += "\\  nom:" + ptrCliente.nombreCompleto();

            }
            ptrCuenta.realizarDeposito(montoDeposito);

            movimiento ptrMovim = new movimiento(movimiento.generarIdMovimientoUnico(),
                    ptrCuenta.getNum_cuenta(), montoDeposito,
                    servicios.ServicioFecha.fechaActualCuenta(), aplicado, motivoDeposito);

            sesion.setAttribute("ptrCuenta", ptrCuenta);
            sesion.setAttribute("ptrMovim", ptrMovim);
            sesion.setAttribute("ptrCliente", ptrCliente);
            response.sendRedirect("/cuentaBancaria/vista/cajero/deposito/confirmarDeposito.jsp");
        } else {
            response.sendRedirect("/cuentaBancaria/vista/cajero.jsp");
        }
    }

    private void hacerDeposito(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        cuenta ptrCuenta = (cuenta) sesion.getAttribute("ptrCuenta");
        movimiento ptrMovim = (movimiento) sesion.getAttribute("ptrMovim");

        cuenta.actualizarMonto(ptrCuenta);
        movimiento.agregarMovimiento(ptrMovim);
        response.sendRedirect("/cuentaBancaria/vista/cajero/cajero.jsp");
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
