<%-- 
    Document   : confirmarRetiro
    Created on : 23/04/2020, 01:15:24 AM
    Author     : Bryan
--%>

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

        <title>Confirmar Retiro</title>
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
        <jsp:useBean class="modelo.cuenta.cuenta" id="ptrCuentaRetiro" scope="session"></jsp:useBean>
        <jsp:useBean class="modelo.movimiento.movimiento" id="ptrMovimRetiro" scope="session"></jsp:useBean>
        <jsp:useBean class="modelo.cliente.Cliente" id="ptrClienteRetiro" scope="session"></jsp:useBean>
            <div id="wrapper">
                <div id="contents"> 
                    <section id="seccion1">
                        <h1>Confirmar Retiro</h1>
                        <article>
                            <form class="formLogin" name="confirmarTransferencia"
                                  action="confirmarTransferencia" method="GET">

                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="etiqueta">
                                                <label for="numCuenta" >numero de Cuenta&nbsp;</label>
                                            </td>
                                            <td class="campo">
                                            <%

                                                out.print("<label for=\"numCuenta\" >"
                                                        + ptrCuentaRetiro.getNum_cuenta() + "&nbsp;</label>");
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
                                                        + ptrCuentaRetiro.nombreCompletoCliente()
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
                                                        + ptrCuentaRetiro.mostrarInfoCuentaRetiro() + "&nbsp;</label>");
                                            %>
                                        </td>

                                    </tr>


                                    <tr>
                                        <td class="etiqueta">
                                            <label for="moneda">Moneda&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                            <%
                                                out.print("<label for=\"moneda\" value=\" " + ptrCuentaRetiro.getMoneda_nombre() + "\">"
                                                        + ptrCuentaRetiro.obtenerMoneda().getDescripcion() + "&nbsp;</label>");

                                            %>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="estado">Estado:&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                            <%                                            int estadoActual = ptrCuentaRetiro.getActiva();
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
                                            <%                          out.print("<label for=\"limieteDiario\" value=\" " + ptrCuentaRetiro.getSaldo_final() + "\">"
                                                        + ptrCuentaRetiro.obtenerMoneda().getSimboloMoneda() + ptrCuentaRetiro.getSaldo_final() + "&nbsp;</label>");

                                            %>

                                        </td>
                                    </tr>



                                    <tr>
                                        <td class="etiqueta">
                                            <label for="montoRetiro">Cantidad del Retiro&nbsp;</label>

                                        </td>
                                        <td class="campo">
                                            <%                                                 out.print("<label for=\"montoRetiro\" value=\" " + 0 + "\">"
                                                        + ptrMovimRetiro.getMonto() + "&nbsp;</label>");

                                            %>
                                        </td>

                                    </tr>


                                    <tr>
                                        <td class="etiqueta">
                                            <label for="nombreDepositante">Nombre del depositante&nbsp;</label>

                                        </td>
                                        <td class="campo">
                                            <%                                                 out.print("<label for=\"nombreDepositante\" value=\" " + 0 + "\">"
                                                        + ptrClienteRetiro.nombreCompleto() + "&nbsp;</label>");

                                            %>
                                        </td>

                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="motivoRetiro">Motivo del del Retiro&nbsp;</label>

                                        </td>
                                        <td class="etiqueta">
                                            <%                                            out.print("<label for=\"motivoRetiro\" value=\" " + 0 + "\">"
                                                        + ptrMovimRetiro.getMovimientocol() + "&nbsp;</label>");

                                            %>
                                        </td>
                                    </tr>



                                    <tr>
                                        <td class="controles" colspan="2">
                                            <button type="submit">Realizar Retiro</button>
                                        </td>
                                    </tr>
                                </tbody>

                            </table>


                        </form>

                    </article>

                </section>





            </div>   
        </div>   

        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>
