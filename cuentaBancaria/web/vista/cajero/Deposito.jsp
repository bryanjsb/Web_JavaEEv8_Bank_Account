<!DOCTYPE html>
<html>
    <head>
        
        <title>Dep�sitos</title>
        <%@ include file="/general.jsp" %>

    </head>
    <body>

        <header class="header">
            <div>
                <div> <h1>Bienvenido al men� de dep�sitos</h1></div>
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
                    <a href="cajero.jsp">aqu�</a>
                    para ir a la p�gina principal.
                </p> 
            </nav>
        </div>
        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>

