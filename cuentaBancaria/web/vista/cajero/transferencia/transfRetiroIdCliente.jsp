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

        <title>Retiro</title>
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
                        <form  name="realizarRetiroIDTRA" 
                               action="realizarRetiroIDTRA" method="GET">
                            <div class="info" style="border: none; ">


                                <jsp:useBean class="modelo.cliente.Cliente" id="CuentasClienteRetiro" scope="session"></jsp:useBean>
                                <jsp:useBean class="modelo.cuenta.cuenta" id="cuentaRetiro" scope="session"></jsp:useBean>


                                    <table>
                                        <tr>
                                            <td class="etiqueta">
                                                <label for="verificarId">Id Cliente&nbsp;</label>

                                            </td>
                                            <td class="campo">
                                                <input type="hidden" size="30" maxlength="9"
                                                       id="verificarId" name="verificarId" autocomplete="off"
                                                       placeholder="<%=CuentasClienteRetiro.getIdCliente()%>"
                                                value="<%=CuentasClienteRetiro.getIdCliente()%>"
                                                />

                                            <label for="verificarId" name="verificarId" 
                                                   value=\"<%=CuentasClienteRetiro.getIdCliente()%>\">
                                                <%=CuentasClienteRetiro.getIdCliente()%></label>
                                        </td>

                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="montoRetiro">Cantidad del retiro&nbsp;</label>

                                        </td>
                                        <td class="campo">
                                            <input type="number" size="30" maxlength="9"
                                                   id="montoRetiro" name="montoRetiro" autocomplete="off"

                                                   />
                                        </td>

                                    </tr>




                                    <tr>
                                        <td class="etiqueta">
                                            <label for="motivoRetiro">Motivo del del retiro&nbsp;</label>

                                        </td>
                                        <td class="campo">
                                            <textarea id="motivoRetiro" name="motivoRetiro"
                                                      cols="35" rows="2"></textarea>
                                        </td>

                                    </tr>



                                    <tr>
                                        <td class="controles" colspan="2">
                                            <button type="submit">Continuar</button>
                                        </td>
                                    </tr>
                                    <tfoot>


                                    </tfoot>
                                </table>

                                <table class="tabla">
                                    <%
                                        out.print(CuentasClienteRetiro.listarCuentahtml());
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
