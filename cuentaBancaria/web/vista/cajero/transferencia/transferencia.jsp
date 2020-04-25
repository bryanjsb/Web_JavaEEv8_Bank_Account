<%@page import="modelo.usuario.Usuario"%>
<%@page import="modelo.cliente.Cliente"%>
<!DOCTYPE html>
<%@page import="servicios.ServicioFecha"%>
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
        <title>Retiro <%= ideUsuario%></title>
    </head>
    <body>

        <header class="header">
            <div>
                <div> <h1>Retiro Cajero</h1></div>

                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                 <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <div>
                    <nav>
                        <ul id="button">
                            <li><a href="controllerLogOut">Cerrar Seccion</a></li>
                            <li><a href="controllerCajero">Pagina principal Cajero</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>

        <div id="wrapper">

            <div id="contents">
                <section id="seccion1">

                    <article>
                        <form class="formLogin" name="buscarClienteRetiroTra"
                              action="buscarClienteRetiroTra" method="GET">


                            <table>
                                <thead>
                                    <tr>
                                <h3>Realizar transferencia al cliente</h3>
                                </tr>
                                <tr>
                                <p>Seleccione el modo de busqueda:</p>
                                </tr>


                                <tr>

                                <div>
                                    <input type="radio" id="numCuenta" name="busquedaRetiro" value="numCuenta">
                                    <label for="numCuenta">Numero Cuenta</label>
                                </div>


                                <div>
                                    <input type="radio" id="idUsuario" name="busquedaRetiro" value="idUsuario"
                                           <label for="idUsuario">Identificaci�n del Usuario</label>  
                                </div>

                                </tr>


                                </thead>

                                <tbody>

                                    <tr>

                                        <td class="campo">
                                            <input type="search" size="40" maxlength="40"
                                                   id="buscarCliente" name="buscarRetiro" autocomplete="off"

                                                   />
                                        </td>

                                        <td class="controles" colspan="10" rowspan="4" >
                                            <button type="submit"
                                                    style="width:190px; height:25px">
                                                Buscar      </button>
                                        </td>
                                    </tr> 
                                </tbody>
                                <tfoot>

                                </tfoot>
                            </table>

                        </form>

                    </article>

                </section>
            </div>


        </div>

        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>