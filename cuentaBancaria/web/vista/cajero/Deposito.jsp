<!DOCTYPE html>
<html>
    <head>
        
        <title>Depósitos</title>
        <%@ include file="/general.jsp" %>

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

