<%-- 
    Document   : header
    Created on : 30/03/2020, 08:22:42 PM
    Author     : Bryan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@ include file="/general.jsp" %>
        <title>cabecera</title>
    </head>
    <body>
        <header class="header">
            <div>
                <div> <h1>Pagina Principal del Banco Universidad Nacional</h1></div>
                <%@page import="servicios.ServicioFecha"%>
                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                 <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <%@ include file="/vista/navegacion.jsp" %>
            </div>
        </header>
    </body>
</html>


