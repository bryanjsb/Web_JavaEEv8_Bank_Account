<%-- 
    Document   : registrarCliente
    Created on : 04/04/2020, 07:26:14 PM
    Author     : Bryan
--%>

<%@page import="modelo.moneda.moneda"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/general.jsp" %>
        <title>Registro Cliente Nuevo</title>
        
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
                                        <label for="login" >Identificación (<em>login</em>):&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30" maxlength="9"
                                               id="login" name="login" autocomplete="off"
                                               placeholder="(max 9 digitos: ej 102340567 )"
                                               />
                                    </td>
                                </tr>


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
                                        <label for="apellidos">Apellidos del usuario&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30" autocomplete="off"
                                               id="apellidos" name="apellidos" />
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
                                               id="telefono" name="telefono" />
                                    </td>
                                </tr>


                                <tr>
                                    <td class="etiqueta">
                                        <label for="clave">Clave de acceso:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30"
                                               id="clave" name="clave" autocomplete="off" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="confirmar">Confirmar la clave:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30"
                                               id="confirmar" name="confirmar" autocomplete="off" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="moneda">Tipo de moneda:&nbsp;</label>
                                    </td>
                                    <td class="campo">

                                        <%
                                            List<moneda> listaMoneda = (List<moneda>) request.getAttribute("listaMonedas");
                                            if (listaMoneda.isEmpty()) {

                                                out.println("<select name=\"moneda\" >");
                                                out.println("<option selected value=\"0\"> no existe tipo moneda </option>");
                                            } else {
                                                out.println("<select name=\"moneda\" >");
                                                out.println("<option selected value=\"0\"> Elige tipo de moneda </option>");

                                                for (moneda m : listaMoneda) {
                                                    out.println(" <option value=\"" + m.getNombreMoneda()+ "\">" 
                                                            + m.getDescripcion() +" - "+m.getNombreMoneda()+"  "
                                                                    +m.getSimboloMoneda()+"</option> ");
                                                }

                                                out.println("  </select>");

                                            }

                                        %>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="controles" colspan="2"rowspan="3">
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
