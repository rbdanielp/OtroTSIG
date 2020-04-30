<%-- 
    Document   : Login
    Created on : 01/05/2018, 12:14:09 AM
    Author     : tecnoinf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>OBDULIO 2030</title>
        <meta charset="utf-8"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib prefix="ct" uri="/WEB-INF/tlds/tag_tsig.tld" %>

    </head>
    <body>
        <!--Inicio Barra de navegacion-->
        <ct:navbar></ct:navbar>
        <!--Fin Barra de navegacion-->
        <!--Login Start+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
        <br>
        <div class="container-fluid " style="align-content: center ; max-width: 450px">
            <div class="row">
                    <div class="col-md-12">
                        <form id="login-form" class="form-horizontal" action="IniciarSesion" method="POST">
                            <div class="form-group input-group" style="width: 100%; align-content: center">
                                <input type="text" class="form-control" 
                                       name="idUsuario" placeholder="Ingresar Usuario..." required>
                            </div>
                            <div class="form-group input-group" style="width: 100%; align-content: center">
                                <input type="password" class ="form-control" required 
                                       name ="password" placeholder="Ingresar Contraseña"
                                       >
                            </div>
                            <div class="checkbox" style="position: absolute">
                                <label>
                                    <input type="checkbox"> Recordarme
                                </label>
                            </div>
                            <br>
                            <br>
                            <div class="form-group input-group" style="width: 100%; align-content: center">
                                <button type="submit" class="btn btn-primary form-control" >Ingresar</button>
                            </div>
                        </form>
                    </div>
            </div>
            No tienes Usuario?  <a href="RegistrarCliente">Registrarse</a>
        </div>
        <!--Login End+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->

    </body>
</html>
