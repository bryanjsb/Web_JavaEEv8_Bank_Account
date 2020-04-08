<%-- 
    Document   : registrarCliente
    Created on : 04/04/2020, 07:26:14 PM
    Author     : Bryan
--%>

<%@page import="modelo.cliente.Cliente"%>
<%@page import="modelo.usuario.Usuario"%>
<%@page import="modelo.usuario.Usuario"%>
<%@page import="modelo.moneda.moneda"%>
<%@page import="java.util.List"%>
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

            Cliente cliente = (Cliente) session.getAttribute("cliente");
            String idClienteNuevo = (String) cliente.getIdCliente();
            String nombre = (String) cliente.getNombre();

            String apellidos = (String) cliente.getApellidos();
            String telefono = (String) cliente.getTelefono();

        %>
        <title>Cuenta Cajero <%= ideUsuario%> Registro Cliente Nuevo</title>

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
                            </ul>
                        </nav>
                    </div>
                </div>
            </header>
        </div>    


        <div id="wrapper">
            <div id="contents" >
                <section>
                    <h1>Registrando Usuario Nuevo.</h1>
                    <article>
                        <h2>Formulario Registro Nuevo</h2>
                        <form class="formLogin" 
                              action="registarNuevoUsuario" method="GET">
                            <table>

                                <tr>
                                    <td class="etiqueta">
                                        <label for="login" >Identificación (<em>login</em>):&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30" maxlength="9"
                                               id="login" name="login" autocomplete="off" 
                                               value=<%=idClienteNuevo%>
                                               />
                                    </td>
                                </tr>


                                <tr>
                                    <td class="etiqueta">
                                        <label for="nombre">Nombre de usuario:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30" 
                                               id="nombre" name="nombre" autocomplete="off" 
                                               />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="apellidos">Apellidos del usuario&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30" autocomplete="off" 
                                               id="apellidos" name="apellidos" 
                                               />
                                    </td>
                                </tr>

                                <tr>
                                    <td class="etiqueta">
                                        <label for="telefono">telefono del usuario&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="tel" size="30" max="8" maxlength="8"
                                               autocomplete="off"  
                                               placeholder="maximo 8 digitos"
                                               id="telefono" name="telefono" 
                                               />
                                    </td>
                                </tr>

                                <tr>
                                    <td class="controles" colspan="2">
                                        <button type="submit">Registrar Usuario</button>
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
