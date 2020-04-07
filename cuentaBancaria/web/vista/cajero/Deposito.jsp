<!DOCTYPE html>
<html>
    <head>

        <title>Depósitos</title>
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
                response.sendRedirect("vista/login/login");
                request.getParameter("finalizoLog");
            }
        %>
    </head>
    <body>

        <header class="header">
            <div>
                <div> <h1>Bienvenido al menú de depósitos</h1></div>
                <%@page import="servicios.ServicioFecha"%>
                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                 <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <%@ include file="/vista/navegacion.jsp" %>
            </div>
        </header>

        <div id="wrapper">
            <div id="contents">

            </div>
            <nav>
                <p>
                    Haga clic
                    <a href="cajero.jsp">aquí</a>
                    para ir a la página principal.
                </p> 
            </nav>
        </div>
        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>

