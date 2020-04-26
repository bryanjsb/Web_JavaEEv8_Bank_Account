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
import modelo.principal.model;
import modelo.transferencia.transferencia;

@WebServlet(name = "controllerCajeroTransferencia",
        urlPatterns = {"/controllerCajeroTransferencia",
            "/buscarClienteRetiroTra",
            "/realizarRetiroIDTRA",
            "/realizarRetiroNumCuentaTRA",
            "/buscarClienteDepositoTRA",
            "/realizarDepositoTRA",
            "/realizarDepositoIdTRA",
            "/confirmarTransferencia"
        })

public class controllerCajeroTransferencia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession(true);

        if (request.getServletPath().equals("/controllerCajeroTransferencia")) {
            paginaTranferenciaRetiro(request, response, sesion);

        }

        if (request.getServletPath().equals("/buscarClienteRetiroTra")) {
            comprobarTipoBusquedaRetiro(request, response, sesion);
        }

        if (request.getServletPath().equals("/realizarRetiroIDTRA")) {
            verificarRetiroPorID(request, response, sesion);
        }

        if (request.getServletPath().equals("/realizarRetiroNumCuentaTRA")) {
            verificarRetiroPorNumCuenta(request, response, sesion);
        }

        if (request.getServletPath().equals("/buscarClienteDepositoTRA")) {
            comprobarTipoBusquedaDeposito(request, response, sesion);
        }

        if (request.getServletPath().equals("/realizarDepositoTRA")) {
            verificarDepositoPorNumCuenta(request, response, sesion);
        }

        if (request.getServletPath().equals("/realizarDepositoIdTRA")) {
            verificarDepositoPorID(request, response, sesion);
        }

        if (request.getServletPath().equals("/confirmarTransferencia")) {
            hacerTransferencia(request, response, sesion);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="paginas. Click on the + sign on the left to edit the code.">
    private void paginaTranferenciaRetiro(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {
        response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/transferencia.jsp");

    }

    private void paginaTransferenciaDeposito(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {
        response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/Deposito.jsp");

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Retiro. Click on the + sign on the left to edit the code.">
    private void comprobarTipoBusquedaRetiro(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String tipoBusqueda = request.getParameter("busquedaRetiro");
        String codigoIngresado = request.getParameter("buscarRetiro");

        if (tipoBusqueda != null && codigoIngresado != null) {

            // comprueba si la busqueda es por numero de cuenta
            if (tipoBusqueda.equals("numCuenta") && cuenta.verificarCuenta(codigoIngresado)) {

                cuenta cuentaCliente = cuenta.obtenerCuenta(codigoIngresado);
                sesion.setAttribute("cuentaRetiro", cuentaCliente);
                sesion.setAttribute("CuentasClienteRetiro", cuentaCliente.obtenerCliente());
                response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/transfRetiroNumeroCuenta.jsp");

            } else // comprueba si la busqueda es por id usuario
            if (tipoBusqueda.equals("idUsuario")
                    && Cliente.verificarCliente(codigoIngresado)) {

                sesion.setAttribute("CuentasClienteRetiro", Cliente.obtenerCliente(codigoIngresado));
                response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/transfRetiroIdCliente.jsp");

            }
        } else {
            // recursivo hasta que ingrese bien los datos
            paginaTranferenciaRetiro(request, response, sesion);

        }

    }

    private void verificarRetiroPorID(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        Cliente ptrClienteRetiro = (Cliente) sesion.getAttribute("CuentasClienteRetiro");
        double montoRetiro = Double.parseDouble(request.getParameter("montoRetiro"));

        cuenta ptrCuentaRetiro = (cuenta) cuenta.obtenerCuenta(request.getParameter("numCuentaCliente"));
        String motivoRetiro = (request.getParameter("motivoRetiro"));

        int aplicado = 1;
        if (ptrCuentaRetiro != null && ptrCuentaRetiro.getCliente_id_cliente() != null) {

            if ((montoRetiro < 0) || (montoRetiro > ptrCuentaRetiro.getSaldo_final())) {
                aplicado = 0;
                response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/transfRetiroIdCliente.jsp");

            } else {
                // si entra es por que es su cuenta 
                ptrCuentaRetiro.realizarRetiro(montoRetiro);

                movimiento ptrMovimRetiro = new movimiento(movimiento.generarIdMovimientoUnico(),
                        ptrCuentaRetiro.getNum_cuenta(), montoRetiro,
                        servicios.ServicioFecha.fechaActualCuenta(), aplicado, motivoRetiro);

                sesion.setAttribute("ptrCuentaRetiro", ptrCuentaRetiro);
                sesion.setAttribute("ptrMovimRetiro", ptrMovimRetiro);
                sesion.setAttribute("ptrClienteRetiro", ptrClienteRetiro);

                sesion.setAttribute("montoRetiro", montoRetiro);
                sesion.setAttribute("motivoRetiro", motivoRetiro);

                paginaTransferenciaDeposito(request, response, sesion);
            }
        } else {
            response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/transfRetiroIdCliente.jsp");
        }

    }

    private void verificarRetiroPorNumCuenta(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        Cliente ptrClienteRetiro = (Cliente) sesion.getAttribute("CuentasClienteRetiro");
        Double montoRetiro = Double.parseDouble(request.getParameter("montoRetiro"));

        cuenta ptrCuentaRetiro = (cuenta) sesion.getAttribute("cuentaRetiro");
        String motivoRetiro = (request.getParameter("motivoRetiro"));

        int aplicado = 1;
        if (ptrCuentaRetiro.getActiva() == 1) {
            if ((montoRetiro < 0) || (montoRetiro > ptrCuentaRetiro.getSaldo_final())) {
                aplicado = 0;
                response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/transfRetiroNumeroCuenta.jsp");

            } else {
                // si entra es por que es su cuenta 
                ptrCuentaRetiro.realizarRetiro(montoRetiro);

                movimiento ptrMovimRetiro = new movimiento(movimiento.generarIdMovimientoUnico(),
                        ptrCuentaRetiro.getNum_cuenta(), montoRetiro,
                        servicios.ServicioFecha.fechaActualCuenta(), aplicado, motivoRetiro);

                sesion.setAttribute("ptrCuentaRetiro", ptrCuentaRetiro);
                sesion.setAttribute("ptrMovimRetiro", ptrMovimRetiro);
                sesion.setAttribute("ptrClienteRetiro", ptrClienteRetiro);

                sesion.setAttribute("montoRetiro", montoRetiro);
                sesion.setAttribute("motivoRetiro", motivoRetiro);

                paginaTransferenciaDeposito(request, response, sesion);
            }

        } else {
            response.sendRedirect("/cuentaBancaria/vista/cajero.jsp");
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Deposito. Click on the + sign on the left to edit the code.">
    private void comprobarTipoBusquedaDeposito(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String tipoBusqueda = request.getParameter("busquedaDeposito");
        String codigoIngresado = request.getParameter("buscarDepostio");

        if (tipoBusqueda != null && codigoIngresado != null) {

            // comprueba si la busqueda es por numero de cuenta
            if (tipoBusqueda.equals("numCuenta") && cuenta.verificarCuenta(codigoIngresado)) {

                cuenta cuentaCliente = cuenta.obtenerCuenta(codigoIngresado);
                sesion.setAttribute("cuentaDeposito", cuentaCliente);
                sesion.setAttribute("CuentasClienteDeposito", cuentaCliente.obtenerCliente());

                response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/depositoNumeroCuenta.jsp");

            } else // comprueba si la busqueda es por id usuario
            if (tipoBusqueda.equals("idUsuario")
                    && Cliente.verificarCliente(codigoIngresado)) {

//              
                sesion.setAttribute("CuentasClienteDeposito", Cliente.obtenerCliente(codigoIngresado));
                response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/depositoIdCliente.jsp");

            }
        } else {
            // recursivo hasta que ingrese bien los datos
            paginaTransferenciaDeposito(request, response, sesion);

        }

    }

    private void verificarDepositoPorID(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        Cliente ptrClienteDeposito = (Cliente) sesion.getAttribute("CuentasClienteDeposito");
        Double montoDeposito = Double.parseDouble((sesion.getAttribute("montoRetiro").toString()));

        cuenta ptrCuentaDeposito = (cuenta) cuenta.obtenerCuenta(request.getParameter("numCuentaCliente"));
        String motivoDeposito = (String) (sesion.getAttribute("motivoRetiro"));

        int aplicado = 1;

        if (ptrClienteDeposito != null && ptrCuentaDeposito != null) {

            if ((montoDeposito < 0)) {
                aplicado = 0;
                response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/depositoIdCliente.jsp");

            } else if (ptrCuentaDeposito.getCliente_id_cliente().equals(ptrClienteDeposito.getIdCliente())) {
                // si entra es por que es su cuenta 
            } else {
                // es por que  usuario  deposita a otra cuenta de otro cliente

                motivoDeposito += "\\  nom:" + ptrClienteDeposito.nombreCompleto();

            }
            ptrCuentaDeposito.realizarDeposito(montoDeposito);

            movimiento ptrMovimDeposito = new movimiento(movimiento.generarIdMovimientoUnico(),
                    ptrCuentaDeposito.getNum_cuenta(), montoDeposito,
                    servicios.ServicioFecha.fechaActualCuenta(), aplicado, motivoDeposito);

            sesion.setAttribute("ptrCuentaDeposito", ptrCuentaDeposito);
            sesion.setAttribute("ptrMovimDeposito", ptrMovimDeposito);
            sesion.setAttribute("ptrClienteDeposito", ptrClienteDeposito);

            sesion.setAttribute("montoDeposito", montoDeposito);
            sesion.setAttribute("motivoDeposito", motivoDeposito);

            response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/confirmarTransferencia.jsp");

        } else {
            response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/depositoIdCliente.jsp");
        }

    }

    private void verificarDepositoPorNumCuenta(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        Cliente ptrClienteDeposito = (Cliente) sesion.getAttribute("CuentasClienteDeposito");
        Double montoDeposito = (Double) (sesion.getAttribute("montoRetiro"));

        cuenta ptrCuentaDeposito = (cuenta) sesion.getAttribute("cuentaDeposito");
        String motivoDeposito = (String) (sesion.getAttribute("motivoRetiro"));

        int aplicado = 1;
        if (ptrCuentaDeposito.getActiva() == 1) {
            if ((montoDeposito < 0)) {
                aplicado = 0;
                response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/Deposito.jsp");

            } else if (ptrCuentaDeposito.getCliente_id_cliente().equals(ptrClienteDeposito.getIdCliente())) {
                // si entra es por que es su cuenta 
            } else {
                // es por que  usuario  deposita a otra cuenta de otro cliente

                motivoDeposito += "\\  nom:" + ptrClienteDeposito.nombreCompleto();

            }
            ptrCuentaDeposito.realizarDeposito(montoDeposito);

            movimiento ptrMovim = new movimiento(movimiento.generarIdMovimientoUnico(),
                    ptrCuentaDeposito.getNum_cuenta(), montoDeposito,
                    servicios.ServicioFecha.fechaActualCuenta(), aplicado, motivoDeposito);

            sesion.setAttribute("ptrCuentaDeposito", ptrCuentaDeposito);
            sesion.setAttribute("ptrMovimDeposito", ptrMovim);
            sesion.setAttribute("ptrClienteDeposito", ptrClienteDeposito);

            sesion.setAttribute("montoDeposito", montoDeposito);
            sesion.setAttribute("motivoDeposito", motivoDeposito);
            response.sendRedirect("/cuentaBancaria/vista/cajero/transferencia/confirmarTransferencia.jsp");
        } else {
            response.sendRedirect("/cuentaBancaria/vista/cajero.jsp");
        }
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HacerTransferencia. Click on the + sign on the left to edit the code.">
    private void hacerRetiro(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        cuenta ptrCuenta = (cuenta) sesion.getAttribute("ptrCuentaRetiro");
        movimiento ptrMovim = (movimiento) sesion.getAttribute("ptrMovimRetiro");

        cuenta.actualizarMonto(ptrCuenta);
        movimiento.agregarMovimiento(ptrMovim);
    }

    private void hacerDeposito(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        cuenta ptrCuenta = (cuenta) sesion.getAttribute("ptrCuentaDeposito");
        movimiento ptrMovim = (movimiento) sesion.getAttribute("ptrMovimDeposito");

        cuenta.actualizarMonto(ptrCuenta);
        movimiento.agregarMovimiento(ptrMovim);

    }

    private void hacerTransferencia(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {
        cuenta cuenOri = (cuenta) sesion.getAttribute("ptrCuentaDeposito");
        cuenta cuenDes = (cuenta) sesion.getAttribute("ptrCuentaRetiro");

        String monto = sesion.getAttribute("montoRetiro").toString();

        model m = model.obtenerInstancia();
        m.agregarTransferencia(new transferencia(m.generarIdTransferenciaUnico(),
                cuenOri.getNum_cuenta(), cuenDes.getNum_cuenta(),
                monto, servicios.ServicioFecha.fechaActualCuenta(), 1));
        hacerRetiro(request, response, sesion);
        hacerDeposito(request, response, sesion);
        response.sendRedirect("/cuentaBancaria/vista/cajero/cajero.jsp");
    }
// </editor-fold>

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
