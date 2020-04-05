<!DOCTYPE html>
<%@page import="servicios.ServicioFecha"%>
<%@page import="modelo.usuario.Usuario"%>

<html>
    <head>

        <meta charset="UTF-8" />
        <base href="http://localhost:8084/cuentaBancaria/">

        <link href="../../css/default.css" rel="stylesheet" type="text/css"/>
        <link href="css/default.css" rel="stylesheet" type="text/css"/>

        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <%

            // Verifica los datos de la sesión para redirigir la página.
            // Observe que si la sesión ha expirado, el servidor asigna
            // una sesión nueva, por lo que los datos del usuario no
            // estarán disponibles.
            if (request.getSession(true).getAttribute("usuario") == null) {
                response.sendRedirect("vista/login/login");
            }
        %>
        <%            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String ideUsuario = usuario.getIdUsuario();
        %>
        <title>Cuenta Cajero <%= ideUsuario%></title>
    </head>

    <body>
        <header class="header">
            <div>

                <div> <h1>Pagina Principal Cajero:<%= ideUsuario%></h1></div>

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
                    <p>
                        <a href="vista/cajero/apertura_cuenta.jsp">Apertura de cuentas</a>
                    </p>
                    <p>
                        <a href="vista/cajero/Deposito.jsp">Depósito</a>
                    </p>
                    <p>
                        <a href="vista/cajero/retiro.jsp">Retiro</a>
                    </p>
                    <p>
                        <a href="vista/cajero/transferencia.jsp">Transferencia en cajas</a>
                    </p>
                    <p>
                        <a href="vista/cajero/acreditacion.jsp">Acreditación de intereses</a>
                    </p>
                </section>
            </div>



        </div>

        <p>
            Haga clic
            <a href="index.jsp">aquí</a>
            para ir a la página principal.
        </p> 


        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>

