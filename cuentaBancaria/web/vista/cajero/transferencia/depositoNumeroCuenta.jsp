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
        <jsp:useBean class="modelo.cuenta.cuenta" id="cuentaDeposito" scope="session"></jsp:useBean>

            <div id="wrapper">
                <div id="contents"> 
                    <section id="seccion1">
                        <h1>deposito por numero de cuenta</h1>
                        <article>
                            <form class="formLogin" name="realizarDepositoTRA"
                                  action="realizarDepositoTRA" method="GET">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="etiqueta">
                                                <label for="numCuenta" >numero de Cuenta&nbsp;</label>
                                            </td>
                                            <td class="campo">
                                            <%

                                                out.print("<label for=\"numCuenta\" >"
                                                        + cuentaDeposito.getNum_cuenta() + "&nbsp;</label>");
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
                                                        + cuentaDeposito.nombreCompletoCliente()
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
                                                        + cuentaDeposito.mostrarInfoCuentaDeposito() + "&nbsp;</label>");
                                            %>
                                        </td>

                                    </tr>


                                    <tr>
                                        <td class="etiqueta">
                                            <label for="moneda">Moneda&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                            <%
                                                out.print("<label for=\"moneda\" value=\" " + cuentaDeposito.getMoneda_nombre() + "\">"
                                                        + cuentaDeposito.obtenerMoneda().getDescripcion() + "&nbsp;</label>");

                                            %>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="limieteDiario">Limite Diario:&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                            <%                          out.print("<label for=\"limieteDiario\" value=\" " + cuentaDeposito.getLimite_transferencia_diaria() + "\">"
                                                        + cuentaDeposito.obtenerMoneda().getSimboloMoneda() + cuentaDeposito.getLimite_transferencia_diaria() + "&nbsp;</label>");

                                            %>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="estado">Estado:&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                            <%                                            int estadoActual = cuentaDeposito.getActiva();
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
                                            <%                          out.print("<label for=\"limieteDiario\" value=\" " + cuentaDeposito.getSaldo_final() + "\">"
                                                        + cuentaDeposito.obtenerMoneda().getSimboloMoneda() + cuentaDeposito.getSaldo_final() + "&nbsp;</label>");

                                            %>

                                        </td>
                                    </tr>

                                    <jsp:useBean class="modelo.cliente.Cliente" id="CuentasClienteDeposito" scope="session"></jsp:useBean>
                                        <tr>
                                            <td class="etiqueta">
                                                <label for="verificarId">Id Cliente&nbsp;</label>

                                            </td>
                                            <td class="campo">
                                                <input type="hidden" size="30" maxlength="9"
                                                       id="verificarId" name="verificarId" autocomplete="off"
                                                       placeholder="<%=CuentasClienteDeposito.getIdCliente()%>"
                                                value="<%=CuentasClienteDeposito.getIdCliente()%>"
                                                />

                                            <label for="verificarId" name="verificarId" 
                                                   value=\"<%=CuentasClienteDeposito.getIdCliente()%>\">
                                                <%=CuentasClienteDeposito.getIdCliente()%></label>
                                        </td>

                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="montoDeposito">Cantidad del deposito&nbsp;</label>

                                        </td>
                                        <td class="campo">
                                            <input type="number" size="30" maxlength="9"
                                                   id="montoDeposito" name="montoDeposito" autocomplete="off"

                                                   />
                                        </td>

                                    </tr>








                                    <tr>
                                        <td class="controles" colspan="2">
                                            <button type="submit">Continuar</button>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr> 
                                        <td class="controles" colspan="4">
                                            <span style="color: #b71540"> El tipo de moneda a depositar se 
                                                convierte al tipo de moneda de 
                                                la cuenta segun los precios de la moneda 
                                                del <%= ServicioFecha.fechaActual()%>
                                            </span>
                                        </td>
                                    </tr> 
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
