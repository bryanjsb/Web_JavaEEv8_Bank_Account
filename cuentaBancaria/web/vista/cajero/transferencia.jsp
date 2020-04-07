<%@page import="modelo.usuario.Usuario"%>
<!DOCTYPE html>
<html>
    <head>

        <%@ include file="/general.jsp" %>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <%
            //comprueba que tenga la misma seccion
            HttpSession sesionActual;
            sesionActual = request.getSession(true);
            out.println(
                    String.format("Sesión actual:&nbsp;%s<br />",
                            sesionActual.getId()));

        %>

        <%            // Verifica los datos de la sesión para redirigir la página.
            // Observe que si la sesión ha expirado, el servidor asigna
            // una sesión nueva, por lo que los datos del usuario no
            // estarán disponibles.
            if (request.getSession(true).getAttribute("usuario") == null) {
                 request.getRequestDispatcher("/seccionCaducada").forward(request, response);
            }
        %>

        <%
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String ideUsuario = usuario.getIdUsuario();
        %>
        <title>Cuenta Cajero <%= ideUsuario%> Transferencia en cajas</title>
    </head>
    <body>
        <div id="wrapper">
            <header>
                <span class="main_title">Bienvenido al menú para realizar una transferencia</span>
            </header>
            <div id="contents">
                <section id="seccion1">

                </section>
            </div>

            <footer>
                <p>
                    Banco centroamericano a su servicio
                </p>
            </footer>
        </div>
        <nav>
            <p>
                Haga clic
                <a href="cajero.jsp">aquí</a>
                para ir a la página principal.
            </p> 
        </nav>
        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>
