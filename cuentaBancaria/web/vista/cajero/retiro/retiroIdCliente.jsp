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
                        <form  name="realizarRetiro" 
                               action="realizarRetiroID" method="GET">
                            <div class="info" style="border: none; ">

                                <table>
                                    <jsp:useBean class="modelo.cliente.Cliente" id="listaCuentasCliente" scope="session"></jsp:useBean>


                                    <%
                                        out.print(listaCuentasCliente.listarCuentahtml());
                                    %>


                                    <tr>
                                        <td class="etiqueta">
                                            <label for="verificarId">Verificar Id Cliente&nbsp;</label>

                                        </td>
                                        <td class="campo">
                                            <input type="search" size="30" maxlength="9"
                                                   id="verificarId" name="verificarId" autocomplete="off"
                                                   placeholder="(max 9 digitos: ej 102340567 )"
                                                   />
                                        </td>

                                    </tr>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="montoRetiro">Cantidad del Retiro&nbsp;</label>

                                        </td>
                                        <td class="campo">
                                            <input type="number" size="30" maxlength="9"
                                                   id="montoRetiro" name="montoRetiro" autocomplete="off"

                                                   />
                                        </td>

                                    </tr>




                                    <tr>
                                        <td class="etiqueta">
                                            <label for="motivoRetiro">Motivo del del Retiro&nbsp;</label>

                                        </td>
                                        <td class="campo">
                                            <textarea id="motivoRetiro" name="motivoRetiro"
                                                      cols="35" rows="2"></textarea>
                                        </td>

                                    </tr>
                                    <tfoot>

                                        <tr>
                                            <td class="controles"  >
                                                <button type="submit"
                                                        style="width:190px; height:25px">
                                                    siguiente      </button>
                                            </td>
                                        </tr> 
                                    </tfoot>
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
