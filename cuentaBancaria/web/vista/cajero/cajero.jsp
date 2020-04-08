<!DOCTYPE html>

<%@page import="modelo.usuario.Usuario"%>

<html>
    <head>

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
                request.getRequestDispatcher("/seccionCaducada").forward(request, response);
            }
        %>
        <%
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String ideUsuario = usuario.getIdUsuario();
        %>
        <title>Cuenta Cajero <%= ideUsuario%></title>
    </head>

    <body>
        <header class="header">
            <div>

                <div> <h1>Pagina Principal Cajero:<%= ideUsuario%></h1></div>
                <%@page import="servicios.ServicioFecha"%>
                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                 <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <div>
                    <nav>
                        <ul id="button">
                            <li><a href="controllerLogOut">Cerrar Seccion</a></li>                   
                            <li><a href="vista/index">Inicio</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>

        <div id="wrapper">

            <div id="contents">

                <section id="seccion1">
                    <h3>Menu Principal</h3>
                    <article>

                        <p>
                            <a href="vista/cajero/apertura_cuenta">Apertura de cuentas</a>
                        </p>
                        <p>
                            <a href="vista/cajero/Deposito.jsp">Dep�sito</a>
                        </p>
                        <p>
                            <a href="vista/cajero/retiro.jsp">Retiro</a>
                        </p>
                        <p>
                            <a href="vista/cajero/transferencia.jsp">Transferencia en cajas</a>
                        </p>
                        <p>
                            <a href="vista/cajero/acreditacion.jsp">Acreditaci�n de intereses</a>
                        </p>
                    </article>
                </section>
            </div>



        </div>

        <p>
            Haga clic
            <a href="index.jsp">aqu�</a>
            para ir a la p�gina principal.
        </p> 


        <%@include file="/vista/footer.jsp" %> 
    </body>

</html>

