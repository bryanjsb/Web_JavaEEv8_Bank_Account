<%-- 
    Document   : login
    Created on : 01/04/2020, 02:23:34 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio Seccion</title>
        <!--<%@ include file="/general.jsp" %>-->
        <jsp:directive.include file="/general.jsp" />
    </head>


    <%@include file="/vista/header.jsp" %> 

    <body class="body2">
        <div id="wrapper">
            <div id="contents" >
                <section>
                    <%

                        // El formulario de ingreso debe comenzar
                        // invalidando la sesión actual.
                        HttpSession sesionActual;
                        sesionActual = request.getSession(true);
                        sesionActual.invalidate();
                        out.println(
                                String.format("Sesión actual:&nbsp;%s<br />",
                                        sesionActual.getId()));

                        // Como la sesión actual es inválida, hay que solicitar
                        // una nueva sesión.
                        // Por razones del ejemplo, la nueva sesión se solicita
                        // aquí, pero es usual que se solicite una nueva sesión
                        // solamente después de validar los datos del usuario.
                        // Observe, sin embargo, que no hay problemas de seguridad
                        // asociados, ya que la nueva sesión, aunque es válida,
                        // no contiene información de ningún atributo.
                        // Los atributos se asocian dentro del servlet que hace la
                        // verificación.
                        sesionActual = request.getSession(true);

                        // Esta sesión SIEMPRE es una sesión nueva,
                        // porque la anterior es inválida.
                        out.println(
                                String.format("Nueva sesión:&nbsp;%s<br />",
                                        sesionActual.getId()));

                    %>


                    <h1>Iniciar Seccion</h1>
                    <article>


                        <h2>Ingrese los datos</h2>
                        <form class="formLogin"  method="GET" action="controllerLogin">
                            <table>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="usuario">Usuario:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="text" size="30"
                                               id="usuario" name="usuario" autocomplete="off" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="etiqueta">
                                        <label for="password">Contraseña:&nbsp;</label>
                                    </td>
                                    <td class="campo">
                                        <input type="password" size="30"
                                               id="password" name="password" autocomplete="off" />
                                    </td>
                                </tr>
                                <tr>

                                    <td class="controles" colspan="2">
                                        <button type="submit">Entrar</button>

                                    </td>
                                </tr>
                            </table>
                        </form>
                    </article>

                </section>

            </div>

        </div>
        <jsp:directive.include file="/vista/footer.jsp" />

    </body>
</html>
