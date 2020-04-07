<%@page import="modelo.usuario.Usuario"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Sistema web de cuentas bancarias</title>
        <jsp:directive.include file="/general.jsp"/>

        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>

        <%
            //comprueba que tenga la misma seccion
            HttpSession sesionActual;
            sesionActual = request.getSession(true);
            out.println(
                    String.format("Sesi�n actual:&nbsp;%s<br />",
                            sesionActual.getId()));

        %>

        <%            // Verifica los datos de la sesi�n para redirigir la p�gina.
            // Observe que si la sesi�n ha expirado, el servidor asigna
            // una sesi�n nueva, por lo que los datos del usuario no
            // estar�n disponibles.
            if (request.getSession(true).getAttribute("usuario") == null) {
                response.sendRedirect("vista/login/login");
            }
        %>
        <%            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String ideUsuario = usuario.getIdUsuario();
        %>
    </head>
    <body>
        <div id="wrapper">
            <header>

                <span class="main_title">Servicios disponibles</span>
            </header>
            <div id="contents">
                <section id="seccion1">
                    <p>
                        Bienvenido :<%=ideUsuario%>
                    </p>
                    <p>
                        <a href="vista/cliente/consulta_cuenta.jsp">Consulta de cuentas y movimientos</a>
                    </p>
                    <p>
                        <a href="vista/cliente/vinculacion_cuenta.jsp">Vinculaci�n de cuentas</a>
                    </p>
                    <p>
                        <a href="vista/cliente/transf_remota.jsp">Transferencia remota</a>
                    </p>
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
                <a href="vista/index.jsp">aqu�</a>
                para ir a la p�gina principal.
            </p> 
        </nav>

        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>

