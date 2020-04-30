<%-- 
    Document   : index
    Created on : 02/05/2018, 03:54:50 PM
    Author     : tecnoinf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>TSIG</title>
        <meta charset="utf-8"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib prefix="ct" uri="/WEB-INF/tlds/tag_tsig.tld" %>
        <script>
        
            function comprobarClave() {
                clave1 = document.fDataUsr.password.value;
                clave2 = document.fDataUsr.confPassword.value;

                if (clave1 === clave2) {
                    return true;

                } else
                    $("#passwordERROR").show();
                return false;
            }

        </script> 
        <script>

            $(document).ready(function () {
                $("#camposErroneo").hide();
                $("#passwordERROR").hide();

                function ValidarCampos() {
                    salida = $("#usrNick").val() !== "";
                    if (salida)
                        salida = $("#passwordID").val() !== "";
                    if (salida)
                        salida = $("#usrNombre").val() !== "";

                    return salida;
                }

                $("#btnConfirmar").click(function () {

                    if (ValidarCampos()) {
                        $("#camposErroneo").hide();
                        if (comprobarClave()) {
                            $("#dataClient-form").submit();
                        } else {
                            $("#passwordERROR").show();
                        }
                    } else {
                        $("#camposErroneo").show();
                    }
                });
            });
        </script>
    </head>
    <body>
        <!--Inicio Barra de navegacion-->
        <ct:navbar></ct:navbar>
        <!--Fin Barra de navegacion-->
        <!--Inicio del Registro+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
        <div class="container-fluid " style="align-content: center ; max-width: 450px">
            <div class="row">
                    <div class="col-md-12">                <h1 style="color: #FFFFFF; margin-top: 0px">Registrarse</h1>
                        <form id="dataClient-form" name="fDataUsr" class="form-horizontal" action="RegistrarCliente" method="POST">

                            <div class="form-group input-group" style="width: 100%; align-content: center">
                                <input type="text" class="form-control" 
                                       name="idUsr" placeholder="Nickname..." required id="usrNick">
                            </div>

                            <div class="form-group input-group" style="width: 100%; align-content: center">
                                <input type="text" class="form-control" name="nombre" value="" placeholder="Nombre..." required id="usrNombre"/>
                            </div>

                            <div class="form-group input-group" style="width: 100%; align-content: center">
                                <input id="passwordId" type="password" class ="form-control" 
                                       name ="password" placeholder="Ingresar Contraseña" required
                                       >
                            </div>
                            <div class="form-group input-group" style="width: 100%; align-content: center">
                                <input id="confParsswordId" type="password" class ="form-control" 
                                       name ="confPassword" placeholder="Confirmar Contraseña" required
                                       >
                            </div>
                            <div class="form-group input-group" style="width: 100%; align-content: center">
                                <input type="text" class="form-control" name="direc" value="" placeholder="Direccion..." required id="usrDirec"/>
                            </div>
                            
                            <!--Se debera considerar si hay paises para listar-->
                            <div class="form-group " style="width: 100%">
                                <label class="col-xs-12" for="filebutton">Qué país sigues?</label>
                                <div class="col-xs-12">
                                    <select name="pais" ; style="width: 100%">
                                        <c:forEach items="${paises}" var="item">
                                            <option value="${item}">${item}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <!--Controlar que se haya elegido un pais-->
                            
                            
                            
                            <div id="passwordERROR" class="alert alert-danger" role="alert">
                                ERROR: Las contraseñas no coinciden.
                            </div>
                            <div id="camposErroneo" class="alert alert-danger" role="alert">
                                Existe algún campo vacío o NO válido
                            </div>

                            <div class="form-group" style="width: 100%; align-content: center">
                                <button id="btnConfirmar" type="button" class="btn btn-primary form-control" style="margin-top: 10px" >Confirmar</button>
                            </div>
                        </form>
                    </div>
            </div>
        </div>
        <!--Fin Registro +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->

    </body>
</html>
