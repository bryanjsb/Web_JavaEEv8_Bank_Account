<%@page import="java.util.List"%>
<%@page import="modelo.cuenta.cuenta"%>
<%@page import="modelo.usuario.Usuario"%>
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
        %>
        <title>Acreditación de intereses</title>
    </head>
    <body>
        <jsp:useBean class="modelo.acreditacion.acreditacion" id="acreditacion" scope="session"></jsp:useBean>
            <header class="header">
                <div>

                    <div> <h1>Pagina Principal Cajero:<%= ideUsuario%></h1></div>
                <%@page import="servicios.ServicioFecha"%>
                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
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
                        <h2>Confirmar Intereses a todas las cuentas</h2>
                    </article>
                    <article>
                        <form action="confirmarAcreditacion">

                            <table>
                                <tr>


                                    <td>
                                        <button>Confirmar Intereses</button>
                                    </td>
                                </tr>
                            </table>


                            <%

                                out.print(acreditacion.mostrarCuentasInteresAplicado());
                            %>


                        </form>
                    </article>
                </section>

                <section id="seccion1">


                </section>
            </div>


        </div>


        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>
