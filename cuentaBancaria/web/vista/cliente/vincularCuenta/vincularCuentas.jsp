<%@page import="modelo.usuario.Usuario"%>
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
        <%
            // Usuario usuario = (Usuario) session.getAttribute("usuario");
            //String ideUsuario = usuario.getIdUsuario();
        %>


        <title>Cuenta Cliente</title>

    </head>
    <body>
        <jsp:useBean class="modelo.usuario.Usuario" id="usuario" scope="session"></jsp:useBean>
        <jsp:useBean class="modelo.cliente.Cliente" id="cliente" scope="session"></jsp:useBean>

            <header class="header">
                <div>

                    <div> <h1>Pagina vinculacion de cuentas favoritas: <%=cliente.nombreCompleto()%></h1></div>
                <%@page import="servicios.ServicioFecha"%>
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

        <div id="wrapper">

            <div id="contents">
                <section id="seccion1">

                    <form class="formLogin" name="buscarClienteFavorito" id="buscarClienteFavorito"
                          action="buscarClienteFavorito" method="GET">


                        <table>
                            <thead>
                                <tr>
                            <h3>Vinculacion de cuentas favoritas</h3>
                            </tr>
                            <tr>
                            <p>Seleccione el modo de busqueda:</p>
                            </tr>


                            <tr>

                            <div>
                                <input type="radio" id="numCuenta" name="busquedaFavorito" value="numCuenta">
                                <label for="numCuenta">Numero Cuenta</label>
                            </div>


                            <div>
                                <input type="radio" id="idUsuario" name="busquedaFavorito" value="idUsuario"
                                       <label for="idUsuario">Identificación del Usuario</label>  
                            </div>

                            </tr>


                            </thead>

                            <tbody>

                                <tr>

                                    <td class="campo">
                                        <input type="search" size="40" maxlength="40"
                                               id="buscarCliente" name="buscarFavorito" autocomplete="off"

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

                </section>
            </div>

        </div>
        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>

