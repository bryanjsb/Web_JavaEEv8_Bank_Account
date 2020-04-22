<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="modelo.cliente.Cliente"%>
<%@page import="modelo.usuario.Usuario"%>
<%@page import="modelo.moneda.moneda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/general.jsp" %>


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

        <%
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String ideUsuario = usuario.getIdUsuario();
            /*
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            String idClienteNuevo = (String) cliente.getIdCliente();
            String nombre = (String) cliente.getNombre();

            String apellidos = (String) cliente.getApellidos();
            String telefono = (String) cliente.getTelefono();
             */
        %>
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <header class="header">
                <div>

                    <div> <h1>Registro Usuario</h1></div>
                    <%@page import="servicios.ServicioFecha"%>
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
        </div>    


        <div id="wrapper">
            <div id="contents" >


                <jsp:useBean class="modelo.cliente.Cliente" id="cliente" scope="session"></jsp:useBean>
                    <section>
                        <h1>Registrando Usuario Nuevo.</h1>
                        <article>
                            <h2>Formulario Registro Nuevo</h2>
                            <form class="formLogin" 
                                  action="confirmacionAceptada" method="GET">
                                <table>

                                    <tr>
                                        <td class="etiqueta">
                                            <label for="login" >Identificación:&nbsp;</label>
                                        </td>
                                        <td class="campo">
                                        <%

                                            out.print("<label for=\"login\" >"
                                                    + cliente.getIdCliente() + "&nbsp;</label>");
                                        %>
                                    </td>
                                </tr>


                                <tr>
                                    <td class="etiqueta">
                                        <label for="nombre">Nombre de usuario:&nbsp;</label>
                                    </td>
                                    <td class="campo">

                                        <%
                                            out.print("<label for=\"nombre\" >"
                                                    + cliente.getNombre() + "&nbsp;</label>");
                                        %>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="apellidos">Apellidos del usuario&nbsp;</label>

                                    </td>
                                    <td class="campo">
                                        <%
                                            out.print("<label for=\"apellidos\" >"
                                                    + cliente.getApellidos() + "&nbsp;</label>");
                                        %>
                                    </td>

                                </tr>

                                <tr>
                                    <td class="etiqueta">
                                        <label for="telefono">telefono del usuario&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <%
                                            out.print("<label for=\"telefono\" value=\" " + cliente.getTelefono() + "\">"
                                                    + cliente.getTelefono() + "&nbsp;</label>");

                                        %>

                                    </td>
                                </tr>

                                <tr>
                                    <td class="etiqueta">
                                        <label for="tipoCuenta">Tipo de cuenta:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <jsp:useBean class="modelo.tipo_cuenta.tipo_cuenta" id="tipoCuenta" scope="session"></jsp:useBean>
                                        <%  out.print("<label for=\"tipoCuenta\" value=\" "
                                                    + tipoCuenta.getDescripcion() + "\">"
                                                    + tipoCuenta.toString() + "&nbsp;</label>");

                                        %>

                                    </td>
                                </tr>

                                <tr>
                                    <td class="etiqueta">
                                        <label for="numeroCuenta">Numero de Cuenta generada:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <%                                     out.print("<label for=\"numeroCuenta\" value=\" "
                                                    + session.getAttribute("numeroCuenta") + "\">"
                                                    + session.getAttribute("numeroCuenta") + "&nbsp;</label>");

                                        %>
                                    </td>
                                </tr>




                                <tr>
                                    <td class="etiqueta">
                                        <label for="limiteDiario">Limite Diario:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <jsp:useBean class="modelo.moneda.moneda" id="tipoMoneda" scope="session"></jsp:useBean>
                                        <%                                            out.print("<label for=\"limiteDiario\" value=\" "
                                                    + session.getAttribute("limiteDiario") + "\">"
                                                    + tipoMoneda.getSimboloMoneda() + " " + session.getAttribute("limiteDiario") + "&nbsp;</label>");

                                        %>
                                    </td>
                                </tr>


                                <tr>
                                    <td class="etiqueta">
                                        <label for="saldoInicial">saldo Inicial:&nbsp;</label>
                                    </td>
                                    <td class="campo">

                                        <%                                            out.print("<label for=\"saldoInicial\" value=\" " + session.getAttribute("saldoInicial") + "\">"
                                                    + tipoMoneda.getSimboloMoneda() + " " + session.getAttribute("saldoInicial") + "&nbsp;</label>");

                                        %>

                                    </td>
                                </tr>

                                <tr>
                                    <td class="etiqueta">
                                        <label for="fechaCreacion">Fecha de creacion:&nbsp;</label>
                                    </td>
                                    <td class="campo">

                                        <%                                            Date fecha = (Date) session.getAttribute("fechaCreacion");
                                            DateFormat fmt = new SimpleDateFormat("dd 'de' MMMM, yyyy", new Locale("es", "CR"));
                                            out.print("<label for=\"fechaCreacion\" value=\" " + fmt.format(fecha) + "\">"
                                                    + fmt.format(fecha) + "&nbsp;</label>");

                                        %>

                                    </td>
                                </tr>

                                <tr>
                                    <td class="controles" colspan="2">
                                        <button type="submit">Crear Cuenta</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </article>

                </section>

            </div>

        </div>

        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>
