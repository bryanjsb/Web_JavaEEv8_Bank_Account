<%@page import="modelo.cuenta.cuenta"%>
<%@page import="servicios.ServicioFecha"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <jsp:directive.include file="/general.jsp"/>


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

        <title>cuenta cliente</title>
    </head>
    <body>

        <jsp:useBean class="modelo.cliente.Cliente" id="listaCuentasFavorita" scope="session"></jsp:useBean>
        <jsp:useBean class="modelo.cuenta.cuenta" id="cuenta" scope="session"></jsp:useBean>

            <header class="header">
                <div>
                    <div> <h1>vinculacion cliente</h1></div>

                    <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                     <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <div>
                    <nav>
                        <ul id="button">
                            <li><a href="controllerLogOut">Cerrar Seccion</a></li>
                            <li><a href="controllerCajero">Pagina principal cliente</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>

        <div id="wrapper">
            <div id="contents"> 
                <section id="seccion1">


                    <article>
                        <form  name="realizarFavoritoID" 
                               action="realizarFavoritoID" method="GET">
                            <div class="info" style="border: none; ">

                                <table>

                                    <tr>
                                        <td class="controles" colspan="2">
                                            <button type="submit">Vincular Cuenta</button>
                                        </td>
                                    </tr>
                                    <tfoot>


                                    </tfoot>
                                </table>


                                <table class="tabla">
                                    <%
                                        out.print(listaCuentasFavorita.listarCuentaVincular());
                                    %>
                                </table>
                            </div>
                        </form>
                    </article>

                </section>

            </div>   
        </div> 
        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>
