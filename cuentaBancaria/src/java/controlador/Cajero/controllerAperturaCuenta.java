/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Cajero;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cliente.Cliente;
import modelo.cuenta.DaoCuenta;
import modelo.cuenta.cuenta;
import modelo.moneda.DaoMoneda;
import modelo.tipo_cuenta.DaoTipo_cuenta;
import modelo.tipo_cuenta.tipo_cuenta;
import servicios.GenerarNumeroCuenta;
import servicios.ServicioFecha;

/**
 *
 * @author Bryan
 */
@WebServlet(name = "controllerAperturaCuenta",
        urlPatterns = {"/controllerAperturaCuenta",
            "/vista/cajero/confirmacionCuentaNueva",
            "/confirmacionAceptada"
        })
public class controllerAperturaCuenta extends HttpServlet {

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
        HttpSession sesion = request.getSession(true);
        if (request.getServletPath().equals("/vista/cajero/confirmacionCuentaNueva")) {

            response.sendRedirect("/cuentaBancaria/vista/cajero/confirmacionCuentaNueva.jsp");
        }

        if (request.getServletPath().equals("/controllerAperturaCuenta")) {

            String moneda = request.getParameter("moneda");
            String numeroCuenta = GenerarNumeroCuenta.
                    generarNumeroCuentaAuto(moneda);

            DaoMoneda daoMoneda = DaoMoneda.obtenerInstancia();

            DaoTipo_cuenta daoTipoCuenta = DaoTipo_cuenta.obtenerInstancia();
            tipo_cuenta tipoCuenta = daoTipoCuenta.obtenerTipo_cuenta(
                    Integer.parseInt(request.getParameter("tipoCuenta"))).get();

            sesion.setAttribute("numeroCuenta", numeroCuenta);

            sesion.setAttribute("tipoCuenta", tipoCuenta);
            sesion.setAttribute("idtipoCuenta", request.getParameter("tipoCuenta"));

            Cliente cliente = (Cliente) sesion.getAttribute("cliente");
            sesion.setAttribute("clienteIdCliente", cliente.getIdCliente());

            sesion.setAttribute("nombreMoneda", moneda);
            sesion.setAttribute("tipoMoneda", daoMoneda.obtenerMoneda(moneda).get());

            sesion.setAttribute("fechaCreacion", ServicioFecha.fechaActualCuenta());

            sesion.setAttribute("limiteDiario", request.getParameter("limiteDiario"));
            sesion.setAttribute("cuentaActiva", 1);
            sesion.setAttribute("saldoInicial", 0);

            sesion.setAttribute("saldoFinal", 0);

            sesion.setAttribute("fechaCreacion", ServicioFecha.fechaActualCuenta());

            response.sendRedirect("vista/cajero/confirmacionCuentaNueva");

        }

        if (request.getServletPath().equals("/confirmacionAceptada")) {

//            cuenta cuenta = new cuenta(
//                    (String) sesion.getAttribute("numeroCuenta"),
//                    Integer.parseInt((String) sesion.getAttribute("idtipoCuenta")),
//                    (String) sesion.getAttribute("clienteIdCliente"),
//                    (String) sesion.getAttribute("nombreMoneda"),
//                    (Date) sesion.getAttribute("fechaCreacion"),
//                    Double.parseDouble((String) sesion.getAttribute("limiteDiario")),
//                    Integer.parseInt((String) sesion.getAttribute("cuentaActiva")),
//                    Double.parseDouble((String) sesion.getAttribute("saldoInicial")),
//                    (Date) sesion.getAttribute("fechaCreacion"),
//                    Double.parseDouble((String) sesion.getAttribute("saldoFinal"))
//            );
            cuenta cuenta = new cuenta("aa", 0, "aa", "aa", ServicioFecha.fechaActualCuenta(),
                    0.0, 0, 0.0, ServicioFecha.fechaActualCuenta(), 0.0);
            DaoCuenta daoCuenta = DaoCuenta.obtenerInstancia();
            daoCuenta.agregarCuenta(cuenta);
            response.sendRedirect("controllerCajero");

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
