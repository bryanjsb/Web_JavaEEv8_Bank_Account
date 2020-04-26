package controlador.Cajero;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cliente.Cliente;
import modelo.cliente.DaoCliente;
import modelo.usuario.Usuario;
import servicios.generadorClave;

@WebServlet(name = "controllerCajero",
        urlPatterns = {"/controllerCajero", "/vista/cajero/registrarCliente",
            "/ClienteBuscar", "/registarNuevoUsuario"})
public class controllerCajero extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getServletPath().equals("/controllerCajero")) {

            paginaPrincipalCajero(request, response);
        }

        if (request.getServletPath().equals("/vista/cajero/registrarCliente")) {

            this.paginaRegistarCliente(request, response);
        }

        if (request.getServletPath().equals("/ClienteBuscar")) {
            this.buscarCliente(request.getParameter("buscarCliente"), request, response);
        }

        if (request.getServletPath().equals("/registarNuevoUsuario")) {
            this.registrarCliente(request, response);
        }

    }

    private void paginaPrincipalCajero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("vista/cajero/cajero.jsp");

    }

    private void paginaRegistarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/cuentaBancaria/vista/cajero/registrarCliente.jsp");
    }

    private void buscarCliente(String id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino = null;
        HttpSession sesion = request.getSession(true);
        DaoCliente dc = DaoCliente.obtenerInstancia();
        if (dc.verificarCliente(id)) {

            sesion.setAttribute("cliente", dc.obtenerCliente(id).get());
            destino = "vista/cajero/crearCuentaCliente";
        } else {

            Cliente cliente = new Cliente();
            cliente.setIdCliente(id);
            cliente.setUsuarioIdUsuario(id);
            sesion.setAttribute("cliente", cliente);
            destino = "vista/cajero/registrarCliente";
        }
        response.sendRedirect(destino);

    }

    private void registrarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);

        Cliente clienteNuevo = (Cliente) sesion.getAttribute("cliente");

        clienteNuevo.setNombre(request.getParameter("nombre"));
        clienteNuevo.setApellidos(request.getParameter("apellidos"));
        clienteNuevo.setTelefono(request.getParameter("telefono"));

        Usuario usuarioNuevo = new Usuario(
                request.getParameter("login"),
                generadorClave.getPassword(), 0, 1
        );

        DaoCliente dc = DaoCliente.obtenerInstancia();

        if (dc.agregarCliente(clienteNuevo, usuarioNuevo)) {
            sesion.setAttribute("cliente", clienteNuevo);

            response.sendRedirect("vista/cajero/crearCuentaCliente");
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
