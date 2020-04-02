<%-- 
    Document   : login
    Created on : 01/04/2020, 02:23:34 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio Seccion</title>
        <link href="../css/default.css" rel="stylesheet" type="text/css"/>
        <link href="../css/login.css" rel="stylesheet" type="text/css"/>
    </head>


    <%@include file="../inicio/header.jsp" %> 

    <body class="body2">
        <div id="wrapper">
            <div id="contents" >
                <section>
                    <h1>Iniciar Seccion</h1>
                    <article>
                        <h2>Ingrese los datos</h2>
                         <form class="formLogin"  method="GET" action="ServicioLogin">
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
                                <input type="text" size="30"
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


        <%@include file="../inicio/redes.jsp" %> 
        <%@include file="../inicio/footer.jsp" %> 
    </body>
</html>
