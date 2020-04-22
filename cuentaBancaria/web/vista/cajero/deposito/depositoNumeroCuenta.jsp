<%@page import="modelo.usuario.Usuario"%>
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

        <title>Deposito</title>
    </head>
    <body>
        <header class="header">
            <div>
                <div> <h1>Deposito Cajero</h1></div>

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
        <jsp:useBean class="modelo.cuenta.cuenta" id="cuenta" scope="session"></jsp:useBean>

            <div id="wrapper">
                <section>
                    <h1>deposito por numero de cuenta</h1>
                    <article>
                        <form class="formLogin" 
                              action="realizarDeposito" method="GET">
                            <table>

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
                                    <label for="tipoCuenta">tipo de cuenta&nbsp;</label>
                                </td>
                                <td class="campo">

                                    <%
                                        out.print("<label for=\"tipoCuenta\" >"
                                                + cuenta.getTipo_cuenta_id_tipo_cuenta() + "&nbsp;</label>");
                                    %>
                                </td>

                            </tr>
                            <tr>
                                <td class="etiqueta">
                                    <label for="idCliente">Id Cliente&nbsp;</label>

                                </td>
                                <td class="campo">
                                    <%
                                        out.print("<label for=\"idCliente\" >"
                                                + cuenta.getCliente_id_cliente() + "&nbsp;</label>");
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
                                                + cuenta.getMoneda_nombre() + "&nbsp;</label>");

                                    %>

                                </td>
                            </tr>

                            <tr>
                                <td class="etiqueta">
                                    <label for="limieteDiario">Limite Diario:&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <%                          out.print("<label for=\"limieteDiario\" value=\" " + cuenta.getLimite_transferencia_diaria() + "\">"
                                                + cuenta.getLimite_transferencia_diaria() + "&nbsp;</label>");

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
                                <td class="etiqueta">
                                    <label for="saldoActual">Saldo Actual:&nbsp;</label>
                                </td>
                                <td class="campo">
                                    <%                          out.print("<label for=\"limieteDiario\" value=\" " + cuenta.getSaldo_final() + "\">"
                                                + cuenta.getSaldo_final() + "&nbsp;</label>");

                                    %>

                                </td>
                            </tr>


                            <tr>
                                <td class="controles" colspan="2">
                                    <button type="submit">Realizar Deposito</button>
                                </td>
                            </tr>
                        </table>
                    </form>

                </article>

            </section>





        </div>   


        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>
