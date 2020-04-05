<%-- 
    Document   : registrarCliente
    Created on : 04/04/2020, 07:26:14 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <base href="http://localhost:8084/cuentaBancaria/">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Cliente Nuevo</title>
        <link href="css/default.css" rel="stylesheet" type="text/css"/>
        <link href="../css/default.css" rel="stylesheet" type="text/css"/>

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
                        <form class="formLogin" name="nuevoUsuario" id="nuevoUsuario"
                              action="ServicioRegistro" method="POST"
                              onsubmit="return verificarRegistro('nuevoUsuario');">
                            <table>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="nombre">Nombre de usuario:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30"
                                               id="nombre" name="nombre" autocomplete="off" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="login">Usuario (<em>login</em>):&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30"
                                               id="login" name="login" autocomplete="off" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="clave">Clave de acceso:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="password" size="30"
                                               id="clave" name="clave" />
                                </tr>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="confirmar">Confirmar la clave:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="password" size="30"
                                               id="confirmar" name="confirmar" />
                                </tr>
                                <tr>
                                    <td class="controles" colspan="2">
                                        <button type="submit">Ingresar</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </article>
                    <article  >
                        <h2>Informacion para el Cliente</h2>
                        <p>
                            Quis enim mi eget, duis consequat pellentesque
                            penatibus, interdum ante nec vitae, sit neque eu
                            est. Integer lorem eleifend ultricies arcu, rerum
                            varius porta dignissim, quis bibendum pharetra
                            magna at. Hendrerit amet a aenean, at proin sit
                            vestibulum praesent. Vehicula in a dolor et, ipsum
                            wisi ut tincidunt ante, praesent odio cursus sed
                            donec. Nullam cras nulla laoreet sodales,
                            pellentesque eleifend turpis aptent, euismod
                            suspendisse necessitatibus pede interdum.
                        </p>
                        <p>
                            Morbi dignissimos fermentum urna, consectetuer a
                            consectetuer integer tincidunt, est eros mi nec
                            sollicitudin. Pulvinar lacinia elit voluptate
                            rutrum, ligula eu eros libero diam. Praesent quam
                            phasellus lacinia, lobortis fermentum et sed,
                            lectus vel sed eget. Nec dolor suspendisse amet
                            posuere, pellentesque habitant fusce ut. Ut velit
                            vulputate semper pede, proin proin ut eum ac,
                            congue ut tincidunt ipsum, dolorem erat tortor nam
                            ultricies.
                        </p>
                    </article>
                </section>

            </div>

        </div>
                    
                     <%@include file="/vista/footer.jsp" %> 
    </body>
</html>
