package controlador.Cajero;

import java.io.IOException;
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

            cuenta cuenta = new cuenta();
            String moneda = request.getParameter("moneda");
            String numeroCuenta = GenerarNumeroCuenta.generarNumeroCuentaAuto(moneda);

            cuenta.setNum_cuenta(numeroCuenta);
            cuenta.setTipo_cuenta_id_tipo_cuenta(Integer.parseInt(request.getParameter("tipoCuenta")));

            Cliente cliente = (Cliente) sesion.getAttribute("cliente");
            cuenta.setCliente_id_cliente(cliente.getIdCliente());

            cuenta.setMoneda_nombre(moneda);
            cuenta.setFecha_creacion(ServicioFecha.fechaActualCuenta());

            double limiteDiario = Double.parseDouble(request.getParameter("limiteDiario"));
            cuenta.setLimite_transferencia_diaria(limiteDiario);

            cuenta.setActiva(1);
            cuenta.setSaldo_inicial(0.0);
            cuenta.setSaldo_final(0.0);

            cuenta.setFecha_ultima_aplicacion(ServicioFecha.fechaActualCuenta());

            sesion.setAttribute("cuenta", cuenta);

            sesion.setAttribute("fechaCreacion", cuenta.getFecha_creacion());
            sesion.setAttribute("saldoInicial", cuenta.getSaldo_inicial());
            sesion.setAttribute("limiteDiario", cuenta.getLimite_transferencia_diaria());
            sesion.setAttribute("numeroCuenta", cuenta.getNum_cuenta());
            response.sendRedirect("vista/cajero/confirmacionCuentaNueva");

        }

        if (request.getServletPath().equals("/confirmacionAceptada")) {

            DaoCuenta daoCuenta = DaoCuenta.obtenerInstancia();
            daoCuenta.agregarCuenta((cuenta) sesion.getAttribute("cuenta"));
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
