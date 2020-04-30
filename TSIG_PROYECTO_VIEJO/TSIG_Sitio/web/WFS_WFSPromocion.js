/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function init() {
    var bounds = [567000, 6135000,
        585000, 6145000];
    var formatWFS = new ol.format.WFS();
    //var tipo = document.getElementById('tipo');
    //var nombre = document.getElementById('nombrePto');


    var formatGML = new ol.format.GML({
        featureNS: 'www.capatsig.uy',
        featureType: 'promocion',
        srsName: 'EPSG:32721',
        geometryName: 'geometry'

    });
    var projection = new ol.proj.Projection({
        code: 'EPSG:32721',
        units: 'm',
        axisOrientation: 'neu',
        global: false
    });
    var xs = new XMLSerializer();
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
                    typenames: 'capatsig:promocion',
                    featureNS: 'www.capatsig.uy',
                    featureType: 'promocion',
                    srsname: 'EPSG:32721',
                    geometryName: 'geometry',
                    bound: '567000, 6135000, 585000, 6145000'
                }
            }).done(function (response) {
                sourceWFS.addFeatures(sourceWFS.getFormat().readFeatures(response));
            });
        },
        //strategy: ol.loadingstrategy.tile(ol.tilegrid.createXYZ()),
        strategy: ol.loadingstrategy.bbox,
        projection: projection
    });
    var layerWFSPoligon = new ol.layer.Vector({
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
    var interaction;
    var interactionSelectPointerMove = new ol.interaction.Select({
        condition: ol.events.condition.pointerMove
    });
    var interactionSelect = new ol.interaction.Select({
        style: new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#FF2828'
            })
        })
    });
    var interactionSnap = new ol.interaction.Snap({
        source: layerWFSPoligon.getSource()
    });
    var calles = new ol.layer.Tile({
        visible: true,
        source: new ol.source.TileWMS({
            url: 'http://localhost:8085/geoserver/wms?',
            params: {
                'FORMAT': "image/png",
                'VERSION': '1.1.1',
                tiled: true,
                STYLES: '',
                LAYERS: 'capatsig:manzanas,capatsig:calles,capatsig:pois'
            }
        })
    });

    var sourceWFSP = new ol.source.Vector({
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
        //strategy: ol.loadingstrategy.tile(ol.tilegrid.createXYZ()),
        strategy: ol.loadingstrategy.bbox,
        projection: projection
    });

    var layerWFSPoint = new ol.layer.Vector({
        visible: true,
        source: sourceWFSP,
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
    var map = new ol.Map({
        target: 'map',
        controls: [],
        interactions: [
            interactionSelectPointerMove,
            new ol.interaction.MouseWheelZoom(),
            new ol.interaction.DragPan()
        ],
        layers: [

            new ol.layer.Image({
                visible: true,
                source: new ol.source.ImageWMS({
                    url: 'http://localhost:8085/geoserver/wms?',
                    params: {
                        'FORMAT': "image/png",
                        'VERSION': '1.1.1',

                        style: '',
                        LAYERS: 'capatsig:montevideo',
                        srsname: 'EPSG:32721',
                        bound: '567000, 6135000, 585000, 6145000',
                        tilesOrigin: 567000 + "," + 6135000,

                    }
                })
            }),
            calles, layerWFSPoint, layerWFSPoligon
        ],
        view: new ol.View({
            projection: projection
        })
    });

    var bounds = [567000, 6135000, 585000, 6145000];
    map.getView().fit(bounds, map.getSize());

    var pointSelect;

    map.on('dblclick', function (event) {

        map.forEachFeatureAtPixel(event.pixel, function (feature) {
            pointSelect = feature;
        });
    });


//wfs-t
    var dirty = {};
    var transactWFS = function (mode, f) {
        var node;
        switch (mode) {
            case 'insert':
                node = formatWFS.writeTransaction([f], null, null, formatGML);
                break;
            case 'update':
                node = formatWFS.writeTransaction(null, [f], null, formatGML);
                break;
            case 'delete':
                node = formatWFS.writeTransaction(null, null, [f], formatGML);
                break;
        }
        var payload = xs.serializeToString(node);
        $.ajax('http://localhost:8085/geoserver/wfs', {
            type: 'POST',
            dataType: 'xml',
            processData: true,
            contentType: 'text/xml',
            data: payload
        }).done(function () {
            sourceWFS.clear();
        });
    };
    $('button').click(function () {
        $(this).siblings().removeClass('btn-active');
        $(this).addClass('btn-active');
        map.removeInteraction(interaction);
        interactionSelect.getFeatures().clear();
        map.removeInteraction(interactionSelect);
        switch ($(this).attr('id')) {

            case 'btnArea':
                interaction = new ol.interaction.Draw({
                    type: 'Polygon',
                    source: layerWFSPoligon.getSource(),
                    geometryName: 'geometry'

                });
                interaction.on('drawend', function (e) {
                    var myAttrValueProm = document.getElementById('promocion').value;
                    var nombreP = pointSelect.get('nombre');

                    var myFeature = e.feature;
                    var extent1 = myFeature.getGeometry().getExtent();
                    var extent2 = pointSelect.getGeometry().getExtent();
                    var intersection = ol.extent.intersects(extent1, extent2);
                    var buffered = ol.extent.getArea(extent1);


                    if (intersection && buffered < 1000000) {
                        myFeature.set('detalle', myAttrValueProm);
                        myFeature.set('poi_nombre', nombreP);
                        transactWFS('insert', myFeature);
                        
                    } else if (intersection === null) {
                        alert("LA PROMOCION NO PERTECE AL PUNTO ELEGIDO");
                        interactionSelectPointerMove.getFeatures().clear();
                        interactionSelect.getFeatures().clear();
                    } else {
                        alert("LA PROMOCION NO DEBE SER MAYOR A 1000 MTS; EL AREA ES :" + buffered + "mts.");
                        interactionSelectPointerMove.getFeatures().clear();
                        interaction.getFeatures().clear();
                    }
                });
                map.addInteraction(interaction);
                break;
            default:
                break;
        }
    });
}

