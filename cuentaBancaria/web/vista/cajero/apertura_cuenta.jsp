<%@page import="modelo.cliente.Cliente"%>
<!DOCTYPE html>
<%@page import="servicios.ServicioFecha"%>
<html>
    <head>
   <%@ include file="/general.jsp" %>
        <title>Apertura de cuenta</title>
        
    </head>
    <body>

        <header class="header">
            <div>
                <div> <h1>Bienvenido al menú para abrir una cuenta</h1></div>

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
        <div id="wrapper">

            <div id="contents">
                <section id="seccion1">
                    <h1>Buscando Cliente</h1>
                    <article>
                        <form class="formLogin" name="nuevoUsuario" id="nuevoUsuario"
                              action="ServicioRegistro" method="GET">
                            <table>
                                
                                <tr>
                                    <td class="etiqueta">
                                        <label for="buscarCliente" >Identificación Cliente:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="search" size="30" maxlength="9"
                                               id="buscarCliente" name="buscarCliente" autocomplete="off"
                                               placeholder="(max 9 digitos: ej 102340567 )"
                                               />
                                    </td>
                                </tr>   
                            </table>

                        </form>

                    </article>
                </section>
            </div>

            <footer>
                <p>
                    Banco centroamericano a su servicio
                </p>
            </footer>
        </div>
        <nav>
            <p>
                Haga clic
                <a href="vista/cajero/cajero.jsp">aquí</a>
                para ir a la página principal.
            </p> 
        </nav>
        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>