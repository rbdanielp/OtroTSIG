


<!DOCTYPE html>
<html>
    <head>

        <title>OBDULIO 2030</title>
        <meta charset="utf-8"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="Openlayers/v3/ol.css" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ol3/3.16.0/ol.css" type="text/css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.indigo-pink.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://openlayers.org/en/v4.6.5/build/ol.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ol3/3.16.0/ol.js"></script>
        <!--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
        
        <link rel="stylesheet" href="../../CSSs/newcss.css" type="text/css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib prefix="ct" uri="/WEB-INF/tlds/tag_tsig.tld" %>



        <!--Fin Barra de navegacion-->
        <!--Inicio del Registro+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->



    </head> 
    <body onload="init()" style="text-align: center;"  background= " images/background.jpg" >
        <!--Inicio Barra de navegacion-->
    <ct:navbar></ct:navbar>
    <!--Fin Barra de navegacion-->

    <h3 class = "badge-info">Bienvenido </h3>
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <h4 class = "badge-info">Registro de Puntos</h4>
                    <table class="table table-striped table-dark">
                        <h6 class = "badge-info">Tipo de Lugar</h6>
                        <select id="tipo" name="tipo" style='font-size: 20px; font-family: "Verdana"; text-align:center'>
                            <option  value="Hotel">Hotel</option>
                            <option  value="Bar">Bar</option>
                            <option value="Estadio">Estadio</option>
                            <option  value="PuntoTuristico">Punto Turistico</option>
                        </select>
                        <div class="form-group input-group" style="width: 100%; align-content: center">
                            <input type="text" class="form-control" 
                                   name="nombrePto" placeholder="Nombre.." required id="nombrePto">
                        </div>
                    </table>
                </div >
            </div>
        </div>
    </div>
    <h6 class = "badge-info">INGRESE PUNTO EN EL MAPA (CLICK EN EL BOTON +)</h6>
    <div id="map"></div>
    <div class="form-group input-group" style="width: 40%; align-content: stretch">
        <script src="/WFS_WMSPunto.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <button id="btnPoint" class="mdl-button mdl-js-button mdl-button--fab mdl-button--colored">
            <i class="material-icons">add_location</i>
        </button>
    </div>


    <!--Fin Registro +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->

</body>
</html>