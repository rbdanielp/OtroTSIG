<%-- 
    Document   : registrarComentario
    Created on : 16/06/2018, 11:59:03 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../../CSSs/newcss.css" type="text/css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib prefix="ct" uri="/WEB-INF/tlds/tag_tsig.tld" %>



        <!--Fin Barra de navegacion-->
        <!--Inicio del Registro+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--> 
        <script>
            function init() {

                var geolocation = new ol.Geolocation({
                    projection: projection
                });
                geolocation.setTracking(true);


// handle geolocation error.
                geolocation.on('error', function (error) {
                    var info = document.getElementById('info');
                    info.innerHTML = error.message;
                    info.style.display = '';
                    console.log("Error al buscar la ubicación")
                });

//Define lo que pasa cuando se cambia la posición
                var accuracyFeature = new ol.Feature();
                geolocation.on('change:accuracyGeometry', function () {
                    accuracyFeature.setGeometry(geolocation.getAccuracyGeometry());
                });

//Crea la feature donde va a estar guardada el punto e ingresa la geolocalizacion
                var positionFeature = new ol.Feature();
                positionFeature.setStyle(new ol.style.Style({
                    image: new ol.style.Circle({
                        radius: 6,
                        fill: new ol.style.Fill({
                            color: '#3399CC'
                        }),
                        stroke: new ol.style.Stroke({
                            color: '#fff',
                            width: 2
                        })
                    })
                }));

//Guarda la nueva posicion en la feature
                geolocation.on('change:position', function () {
                    var coordinates = geolocation.getPosition();
                    positionFeature.setGeometry(coordinates ?
                            new ol.geom.Point(coordinates) : null);
                });

//Define la capa donde se guarda la localización y la agrega en el mapa
                new ol.layer.Vector({
                    map: map,
                    source: new ol.source.Vector({
                        features: [accuracyFeature, positionFeature],
                        srsname: 'EPSG:32721',
                        geometryName: 'geometry',
                        bound: '567000, 6135000, 585000, 6145000'
                    })
                });

//*/
                var pureCoverage = false;
// if this is just a coverage or a group of them, disable a few items,
// and default to jpeg format
                var format = 'image/png';
                var bounds = [567000, 6135000, 585000, 6145000];

                /**
                 * Elements that make up the popup.
                 */
                var container = document.getElementById('popup');

                /**
                 * Create an overlay to anchor the popup to the map.
                 */
                var overlay = new ol.Overlay({
                    element: container,
                    position: [125, 250],
                    positioning: 'bottom-center',
                    stopEvent: false
                });


                /**
                 * Add a click handler to hide the popup.
                 * @return {boolean} Don't follow the href.
                 */







                var untiled = new ol.layer.Image({
                    source: new ol.source.ImageWMS({
                        ratio: 1,
                        url: 'http://localhost:8085/geoserver/capatsig/wms',
                        params: {'FORMAT': format,
                            'VERSION': '1.1.1',
                            STYLES: '',
                            LAYERS: 'capatsig:montevideo,capatsig:manzanas',
                        }
                    })
                });
                var tiled = new ol.layer.Tile({
                    visible: true,
                    source: new ol.source.TileWMS({
                        url: 'http://localhost:8085/geoserver/capatsig/wms',
                        params: {'FORMAT': format,
                            'VERSION': '1.1.1',
                            tiled: true,
                            STYLES: '',
                            LAYERS: 'capatsig:calles',
                            tilesOrigin: 567000 + "," + 6135000
                        }
                    })
                });
                var sourceWFS = new ol.source.Vector({
                    format: new ol.format.GeoJSON(),
                    loader: function (extent) {
                        $.ajax('http://localhost:8085/geoserver/wfs', {
                            type: 'GET',
                            data: {

                                service: 'WFS',
                                version: '1.1.0',
                                request: 'GetFeature',
                                outputFormat: 'application/json',
                                typenames: 'capatsig:pois',
                                featureNS: 'www.capatsig.uy',
                                featureType: 'pois',
                                srsname: 'EPSG:32721',
                                geometryName: 'geometry',
                                bound: '567000, 6135000, 585000, 6145000'
                            }
                        }).done(function (response) {
                            sourceWFS.addFeatures(sourceWFS.getFormat().readFeatures(response));
                        });
                    },
                    strategy: ol.loadingstrategy.bbox,
                    projection: projection
                });


                var layerWFS = new ol.layer.Vector({
                    visible: true,
                    source: sourceWFS,
                    style: new ol.style.Style({
                        fill: new ol.style.Fill({
                            color: 'rgba(255, 255, 255, 0.2)'
                        }),
                        stroke: new ol.style.Stroke({
                            color: '#ffcc33',
                            width: 2
                        }),
                        image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
                            anchor: [0.5, 46],
                            anchorXUnits: 'fraction',
                            anchorYUnits: 'pixels',
                            src: '/images/icons8-marker-50.png'
                        }))
                    })

                });

                var projection = new ol.proj.Projection({
                    code: 'EPSG:32721',
                    units: 'm',
                    axisOrientation: 'neu',
                    global: false
                });
                var map = new ol.Map({
                    controls: ol.control.defaults({
                        attribution: true
                    }), //.extend([mousePositionControl]),
                    target: 'map',
                    layers: [
                        untiled,
                        tiled,
                        layerWFS
                    ],
                    overlays: [overlay],
                    view: new ol.View({
                        projection: projection
                    })
                });

                map.on('pointermove', function (event) {

                    map.forEachFeatureAtPixel(event.pixel, function (feature) {
                        var geometry = feature.getGeometry();
                        var coord = geometry.getCoordinates();
                        //        overlay.setPosition(coord);
                        //       console.log(feature.getName());
                        document.getElementById('popup-content').innerHTML = '<p>Nombre: </p><code>' + feature.get('nombre') +
                                '</code>' + '<p>Lugar de Interés: </p><code>' + feature.get('dtype') + '</code>';

                    });
                });

                var pointId;
                var dtype;
                map.on('singleclick', function (event) {
                    map.forEachFeatureAtPixel(event.pixel, function (feature) {
                        pointId = feature.get('gid');
                        dtype = feature.get('dtype');
                        document.getElementById('puntoId').value = pointId;
                        document.getElementById('dtype').value = dtype;

                        console.log(pointId);
                    });
                });



                var comentario = document.getElementById('comentario').value;
                var puntaje = document.getElementById('puntaje').value;


                map.getView().on('change:resolution', function (evt) {
                    var resolution = evt.target.get('resolution');
                    var units = map.getView().getProjection().getUnits();
                    var dpi = 25.4 / 0.28;
                    var mpu = ol.proj.METERS_PER_UNIT[units];
                    var scale = resolution * mpu * 39.37 * dpi;
                    if (scale >= 9500 && scale <= 950000) {
                        scale = Math.round(scale / 1000) + "K";
                    } else if (scale >= 950000) {
                        scale = Math.round(scale / 1000000) + "M";
                    } else {
                        scale = Math.round(scale);
                    }
//        document.getElementById('scale').innerHTML = "Scale = 1 : " + scale;
                });
                map.getView().fit(bounds, map.getSize());

                function setWidth(size) {
                    var mapDiv = document.getElementById('map');
                    var wrapper = document.getElementById('wrapper');
                    if (size === "auto") {
                        // reset back to the default value
                        mapDiv.style.width = null;
                        wrapper.style.width = null;
                    } else {
                        mapDiv.style.width = size + "px";
                        wrapper.style.width = size + "px";
                    }
                    // notify OL that we changed the size of the map div
                    map.updateSize();
                }

                function setHeight(size) {
                    var mapDiv = document.getElementById('map');
                    if (size === "auto") {
                        // reset back to the default value
                        mapDiv.style.height = null;
                    } else {
                        mapDiv.style.height = size + "px";
                    }
                    // notify OL that we changed the size of the map div
                    map.updateSize();
                }
            }
        </script>
    </head> 
    <body onload="init()" style="text-align: center;"  background= " images/background.jpg" >
        <!--Inicio Barra de navegacion-->
        <ct:navbar></ct:navbar>
        <!--Fin Barra de navegacion-->

        <h3 class = "badge-info">Bienvenido </h3>
        <form id="regComentario" class="form-horizontal" action="/RegistrarComentario" method="POST"> 
            <input type="hidden"  name="puntoId" id="puntoId" />
            <input type="hidden"  name="dtype" id="dtype" />
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-12">

                            <h4 class = "badge-info">Registrar Comentario</h4>
                            <div class="form-group input-group" style=" height: 100px;width: 100%; align-content: space-between">
                                <input type="text" class="form-control" 
                                       name="comentario" placeholder="Comentario.." required id="comentario">
                            </div>

                            <h4 class = "badge-info">Ingrese Puntaje</h4>
                            <div class="form-group input-group" style="height: 100px;width: 100%; align-content: space-between">
                                <input class="form-control" type="number" 
                                       min="1" max="5" step="any" name="puntaje" placeholder="Puntaje.." required id="puntaje">
                            </div>

                        </div >
                    </div>
                </div>
            </div>
            <h6 class = "badge-info">SELECCIONE PUNTO EN EL MAPA</h6>
            <div id="txt"></div>
            <div id="map">
                <div id="popup" class="ol-popup">
                    <div id="popup-closer"></div>
                    <div id="popup-content"></div>
                </div>
            </div>
            <div id="info" style="display: none;"></div>
            <div class="submitAndHiddens">
                <input class = "badge-info" style=" width: 400px; padding:12px" type="submit" class="submit" value="Registrar" />
            </div>

        </form>  


        <!--Fin Registro +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->

    </body>
</html>
