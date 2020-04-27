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

        <title>Confirmar vinculacion</title>
    </head>
    <body>
        <header class="header">
            <div>
                <div> <h1>vinculacion </h1></div>

                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                 <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <div>
                    <nav>
                        <ul id="button">
                            <li><a href="controllerLogOut">Cerrar Seccion</a></li>
                            <li><a href="controllerCliente">Pagina principal Cliente</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <jsp:useBean class="modelo.cuenta.cuenta" id="cuenta" scope="session"></jsp:useBean>
            <div id="wrapper">
                <div id="contents"> 
                    <section id="seccion1">
                        <h1>Confirmar vinculacion</h1>
                        <article>
                            <form class="formLogin" 
                                  action="confirmarVinculacion" method="GET">

                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="etiqueta">
                                                <label for="numCuenta" >numero de Cuenta&nbsp;</label>
                                            </td>
                                            <td class="campo">
                                            <%

                                                out.print("<label for=\"numCuenta\" >"
                                                        + cuenta.getNum_cuenta() + "&nbsp;</label>");
                                            %>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="duenoCuenta" >Dueño de la cuenta&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                            <%
                                                out.print("<label for=\"duenoCuenta\" >"
                                                        + cuenta.nombreCompletoCliente()
                                                        + "&nbsp;</label>");
                                            %>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="tipoCuenta">tipo de cuenta&nbsp;</label>
                                        </td>
                                        <td class="campo">

                                            <%
                                                out.print("<label for=\"tipoCuenta\" >"
                                                        + cuenta.mostrarInfoCuentaRetiro() + "&nbsp;</label>");
                                            %>
                                        </td>

                                    </tr>


                                    <tr>
                                        <td class="etiqueta">
                                            <label for="moneda">Moneda&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                            <%
                                                out.print("<label for=\"moneda\" value=\" " + cuenta.getMoneda_nombre() + "\">"
                                                        + cuenta.obtenerMoneda().getDescripcion() + "&nbsp;</label>");

                                            %>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="estado">Estado:&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                            <%                                            int estadoActual = cuenta.getActiva();
                                                if (estadoActual == 0) {
                                                    out.print("<label for=\"limieteDiario\" value=\" " + estadoActual + "\">"
                                                            + "inabilitada" + "&nbsp;</label>");

                                                } else {
                                                    out.print("<label for=\"limieteDiario\" value=\" " + estadoActual + "\">"
                                                            + "Activa" + "&nbsp;</label>");
                                                }
                                            %>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td class="controles" colspan="2">
                                            <button type="submit">Realizar Vinculacion</button>
                                        </td>


                                    </tr>
                                </tbody>

                            </table>


                        </form>

                        <form name="cancelar" action="cancelar">
                            <td class="controles" colspan="2">
                                <button  type="submit">cancelar Vinculacion</button>
                            </td>
                        </form>
                    </article>

                </section>





            </div>   
        </div>   

        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>
