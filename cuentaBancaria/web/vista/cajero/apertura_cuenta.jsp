<!DOCTYPE html>
<%@page import="servicios.ServicioFecha"%>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Apertura de cuenta</title>
        <base href="http://localhost:8084/cuentaBancaria/">

        <link href="../../css/default.css" rel="stylesheet" type="text/css"/>
        <link href="css/default.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <header class="header">
            <div>
                <div> <h1>Bienvenido al menú para abrir una cuenta</h1></div>

                <div><p class="headerFecha" >Fecha: <%= ServicioFecha.fechaActual()%> </p></div>
                 <!--<div><p class="headerHora" >Hora: <%= ServicioFecha.HoraActual()%> </p></div>-->
                <div>
                    <nav>
                        <ul id="button">
                            <li><a href="vista/login/login">Iniciar Seccion</a></li>                   
                            <li><a href="vista/index">Inicio</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <div id="wrapper">

            <div id="contents">
                <section id="seccion1">

                </section>
            </div>

            <footer>
                <p>
                    Banco centroamericano a su servicio
                </p>
            </footer>
        </div>
        <nav>
            <p>
                Haga clic
                <a href="vista/cajero/cajero.jsp">aquí</a>
                para ir a la página principal.
            </p> 
        </nav>
        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>