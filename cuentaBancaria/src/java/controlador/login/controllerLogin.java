package controlador.login;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.usuario.DaoUsuario;
import modelo.usuario.Usuario;

@WebServlet(name = "controllerLogin",
        urlPatterns = {"/controllerLogin", "/vista/login/login"

        })
public class controllerLogin extends HttpServlet {

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

        if (request.getServletPath().equals("/vista/login/login")) {
            this.paginaLogin(request, response);
        }

        if (request.getServletPath().equals("/controllerLogin")) {
            log(request, response);

        }

    }

    private void paginaLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/vista/login/login.jsp").forward(request, response);

    }

    private void log(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("password");
        boolean usuarioValido = false;
        DaoUsuario daoUsuario = DaoUsuario.obtenerInstancia();
        if (usuario != null && clave != null) {
            usuarioValido = daoUsuario.verificarUsuario(usuario = checkId(usuario), clave);

        }

        if (usuarioValido) {

            Optional<Usuario> tipoUsuario = daoUsuario.obtenerUsuario(usuario);

            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("usuario", tipoUsuario.get());

            // Fija el tiempo de expiración de la sesión
            // en 3 minutos, independientemente de lo especificado
            // en la configuración de la aplicación.
            sesion.setMaxInactiveInterval(60 * 30);

            String redirecion = redireccionarUsuario(tipoUsuario);
            response.sendRedirect(redirecion);
        } else {

            // Aquí se puede redirigir la página a una diferente
            // dependiendo del tipo de error. Por ejemplo, se puede
            // utilizar una página para solicitar al usuario que
            // se registre en el sitio.
            response.sendRedirect("vista/login/login");
        }

    }

    private String redireccionarUsuario(Optional<Usuario> usuario) {
        return (usuario.get().getRol() == 0) ? "controllerCajero"
                : "controllerCliente";
    }

    private String checkId(String txt) {
        String r = txt;
        String opcion1 = "([0-9,A])-?([0-9]{4})-?([0-9]{4})";
        Pattern pat = Pattern.compile(opcion1);
        Matcher m = pat.matcher(txt);
        if (m.find()) {
            r = String.format("%s%s%s", m.group(1), m.group(2), m.group(3));
        }

        return r;
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
