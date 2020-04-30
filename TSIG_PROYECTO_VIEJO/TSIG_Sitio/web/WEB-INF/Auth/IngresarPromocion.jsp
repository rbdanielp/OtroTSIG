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
        <script src="https://cdn.rawgit.com/bjornharrtell/jsts/gh-pages/1.4.0/jsts.min.js"></script>
        <script src='https://api.mapbox.com/mapbox.js/v2.2.2/mapbox.js'></script>
        <script src='https://api.mapbox.com/mapbox.js/plugins/turf/v2.0.2/turf.min.js'></script>
        <script src='https://api.tiles.mapbox.com/mapbox.js/plugins/turf/v1.3.0/turf.min.js'></script>
        <script src='https://npmcdn.com/@turf/turf/turf.min.js'></script>
        <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' />
        <script src='https://api.tiles.mapbox.com/mapbox.js/v2.1.6/mapbox.js'></script>
        <script src='https://api.tiles.mapbox.com/mapbox.js/plugins/leaflet-label/v0.2.1/leaflet.label.js'></script>
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


        <h3 class = "badge-info">BIENVENIDO</h3>
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-12">
                        <h4 class = "badge-info">REGISTRO DE PROMOCIONES</h4>
                        <h6 class = "badge-info">DESCRIPCION DE PROMOCION</h6>
                        <div class="form-group input-group" style=" height: 100px;width: 100%; align-content: space-between">
                            <input id="promocion" type="text" class ="form-control" 
                                   name ="promocion" placeholder="Promocion" required
                                   >
                        </div>

                        <h6 class = "badge-info">PRIMERO ELIJA PUNTO REFERENTE A PROMOCION (DOBLE CLICK SOBRE EL PUNTO)</h6>
                        <h6 class = "badge-info">LUEGO ELIJA EL AREA DE LA PROMOCION (CLICK BOTON ROJO) </h6>
                    </div >
                </div>
            </div>
        </div>
        <div id="map" ></div>
        <div class="form-group input-group" style="width: 40%; align-content: stretch">
            <script src="/WFS_WFSPromocion.js"></script>
            <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
            <button id="btnArea" class="mdl-button mdl-js-button mdl-button--fab mdl-button--colored">
                <i class="material-icons">signal_cellular_null</i>
            </button>
        </div>

        <!--Fin Registro +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->

    </body>
</html>

