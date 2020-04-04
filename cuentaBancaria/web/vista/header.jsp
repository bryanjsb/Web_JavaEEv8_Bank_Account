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
        <base href="http://localhost:8084/cuentaBancaria/">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cabecera</title>
        <link href="../css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header class="header">
            <div>
                <div> <h1>Pagina Principal del Banco Universidad Nacional</h1></div>

                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                 <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <%@ include file="/vista/navegacion.jsp" %>
            </div>
        </header>
    </body>
</html>


