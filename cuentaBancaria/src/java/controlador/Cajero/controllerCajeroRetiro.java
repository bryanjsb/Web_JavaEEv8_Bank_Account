/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Bryan
 */
@WebServlet(name = "controllerCajeroRetiro",
        urlPatterns = {"/controllerCajeroRetiro",
            "/buscarClienteRetiro",
            "/realizarRetiroID",
            "/vista/cajero/retiro/retiro",
            "/confirmarRetiro",
            "/realizarRetiroNumCuenta"
        })
public class controllerCajeroRetiro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession(true);

        if (request.getServletPath().equals("/vista/cajero/retiro/retiro")) {
            paginaRetiro(request, response);

        }
        if (request.getServletPath().equals("/buscarClienteRetiro")) {

            comprobarTipoBusqueda(request, response, sesion);

        }

        if (request.getServletPath().equals("/realizarRetiroID")) {
            verificarRetiroPorID(request, response, sesion);
        }

        if (request.getServletPath().equals("/realizarRetiroNumCuenta")) {
            verificarRetiroPorNumCuenta(request, response, sesion);
        }

        if (request.getServletPath().equals("/confirmarRetiro")) {
            hacerRetiro(request, response, sesion);
        }
    }

    private void paginaRetiro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/retiro.jsp");

    }

    private void comprobarTipoBusqueda(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String tipoBusqueda = request.getParameter("busquedaRetiro");
        String codigoIngresado = request.getParameter("buscarRetiro");

        if (tipoBusqueda != null && codigoIngresado != null) {

            // comprueba si la busqueda es por numero de cuenta
            if (tipoBusqueda.equals("numCuenta") && cuenta.verificarCuenta(codigoIngresado)) {

                cuenta cuentaCliente = cuenta.obtenerCuenta(codigoIngresado);
                sesion.setAttribute("cuenta", cuentaCliente);

                response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/retiroNumeroCuenta.jsp");

            } else // comprueba si la busqueda es por id usuario
            if (tipoBusqueda.equals("idUsuario")
                    && Cliente.verificarCliente(codigoIngresado)) {

                sesion.setAttribute("listaCuentasCliente", Cliente.obtenerCliente(codigoIngresado));
                response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/retiroIdCliente.jsp");

            }
        } else {
            // recursivo hasta que ingrese bien los datos
            paginaRetiro(request, response);

        }

    }

    private void verificarRetiroPorID(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String numCuenta = request.getParameter("numCuentaCliente");

        if (cuenta.verificarCuenta(numCuenta)) {
            String verificarId = request.getParameter("verificarId");

            Double montoRetiro = Double.parseDouble(request.getParameter("montoRetiro"));
            cuenta ptrCuenta = cuenta.obtenerCuenta(numCuenta);
            String motivoRetiro = (request.getParameter("motivoRetiro"));

            Cliente ptrCliente = Cliente.obtenerCliente(verificarId);

            int aplicado = 1;
            if ((montoRetiro < 0) || (montoRetiro > ptrCuenta.getSaldo_final())) {
                aplicado = 0;
                response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/retiroIdCliente.jsp");

            } else if (ptrCuenta.getCliente_id_cliente().equals(verificarId)) {
                // si entra es por que es su cuenta 
                ptrCuenta.realizarRetiro(montoRetiro);

                movimiento ptrMovim = new movimiento(movimiento.generarIdMovimientoUnico(),
                        ptrCuenta.getNum_cuenta(), montoRetiro,
                        servicios.ServicioFecha.fechaActualCuenta(), aplicado, motivoRetiro);

                sesion.setAttribute("ptrCuenta", ptrCuenta);
                sesion.setAttribute("ptrMovim", ptrMovim);
                sesion.setAttribute("ptrCliente", ptrCliente);
                response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/confirmarRetiro.jsp");
            } else {

                response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/retiroIdCliente.jsp");
            }

        } else {
            response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/retiroIdCliente.jsp");
        }

    }

    private void verificarRetiroPorNumCuenta(HttpServletRequest request,
            HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {

        String verificarId = request.getParameter("verificarId");

        Double montoRetiro = Double.parseDouble(request.getParameter("montoRetiro"));

        cuenta ptrCuenta = (cuenta) sesion.getAttribute("cuenta");
        String motivoRetiro = (request.getParameter("motivoRetiro"));

        Cliente ptrCliente = Cliente.obtenerCliente(verificarId);

        int aplicado = 1;
        if (ptrCuenta.getActiva() == 1 && ptrCuenta != null) {
            if ((montoRetiro < 0) || (montoRetiro > ptrCuenta.getSaldo_final())) {
                aplicado = 0;
                response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/retiro.jsp");

            } else if (ptrCuenta.getCliente_id_cliente().equals(verificarId)) {
                // si entra es por que es su cuenta 
                ptrCuenta.realizarRetiro(montoRetiro);

                movimiento ptrMovim = new movimiento(movimiento.generarIdMovimientoUnico(),
                        ptrCuenta.getNum_cuenta(), montoRetiro,
                        servicios.ServicioFecha.fechaActualCuenta(), aplicado, motivoRetiro);

                sesion.setAttribute("ptrCuenta", ptrCuenta);
                sesion.setAttribute("ptrMovim", ptrMovim);
                sesion.setAttribute("ptrCliente", ptrCliente);
                response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/confirmarRetiro.jsp");
            } else {
                // es por que  usuario  deposita a otra cuenta de otro cliente

                response.sendRedirect("/cuentaBancaria/vista/cajero/retiro/retiroNumeroCuenta.jsp");

            }

        } else {
            response.sendRedirect("/cuentaBancaria/vista/cajero.jsp");
        }
    }

    private void hacerRetiro(HttpServletRequest request,
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
