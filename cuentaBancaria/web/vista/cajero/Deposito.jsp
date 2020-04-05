<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Depósitos</title>
        <base href="http://localhost:8084/cuentaBancaria/">

        <link href="../../css/default.css" rel="stylesheet" type="text/css"/>
        <link href="css/default.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <header class="header">
            <div>
                <div> <h1>Bienvenido al menú de depósitos</h1></div>
                <%@page import="servicios.ServicioFecha"%>
                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                 <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <%@ include file="/vista/navegacion.jsp" %>
            </div>
        </header>
             
       <div id="wrapper">
            <div id="contents">
                
            </div>
            <nav>
                <p>
                    Haga clic
                    <a href="cajero.jsp">aquí</a>
                    para ir a la página principal.
                </p> 
            </nav>
        </div>
        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>

