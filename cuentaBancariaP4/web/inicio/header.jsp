<%-- 
    Document   : header
    Created on : 30/03/2020, 08:22:42 PM
    Author     : Bryan
--%>
<%@page import="servicios.ServicioFecha"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cabecera</title>

    </head>
    <body>
        <header class="header">
            <div>
                <div> <h1>Pagina Principal del Banco Universidad Nacional</h1></div>

                <div><p class="headerp" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                <%@ include file="navegacion.jsp" %>
            </div>
        </header>
    </body>
</html>


