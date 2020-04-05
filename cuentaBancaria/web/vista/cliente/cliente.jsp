<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Sistema web de cuentas bancarias</title>
        <link href="css/default.css" rel="stylesheet" type="text/css"/>
        <link href="../css/default.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <header>

                <span class="main_title">Servicios disponibles</span>
            </header>
            <div id="contents">
                <section id="seccion1">
                    <p>
                        <a href="vista/cliente/consulta_cuenta.jsp">Consulta de cuentas y movimientos</a>
                    </p>
                    <p>
                        <a href="vista/cliente/vinculacion_cuenta.jsp">Vinculación de cuentas</a>
                    </p>
                    <p>
                        <a href="vista/cliente/transf_remota.jsp">Transferencia remota</a>
                    </p>
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
                <a href="vista/index.jsp">aquí</a>
                para ir a la página principal.
            </p> 
        </nav>

        <%@include file="/vista/footer.jsp" %> 
    </body>
</html>

