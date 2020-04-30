/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Adds user geolocalization
//Define elemento del tipo geolocalization
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
        layerWFS,
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
map.on('singleclick', function (event) {

    map.forEachFeatureAtPixel(event.pixel, function (feature) {
        pointId = feature.get('gid');
        
        console.log(pointSelect);
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


/*    
 var pointSelect;
 map.on('singleclick', function (event) {
 
 map.forEachFeatureAtPixel(event.pixel, function (feature) {
 pointSelect = feature;
 
 console.log(pointSelect);
 
 });
 });
 
 
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
 
 
 
 var poinId;
 var username = '<%= Session["nickUsr"] %>';
 console.log(pointSelect);
 
 function dbInsert() {
 
 var
 connection = new ActiveXObject("postgres.Connection"),
 recordSet = new ActiveXObject("postgres.Recordset"),
 connectionString = '';
 
 connection.Open(connectionString);
 recordSet.Open(
 "INSERT INTO comentario VALUES ('" +
 document.getElementById('comentario').value + "','" +
 document.getElementById('puntaje').value +
 poinId + "','" + username,
 "')",
 connection
 );
 
 recordSet.close;
 connection.close;
 
 }
 
 
 
 
 /* 
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
 
 case 'btnEdit':
 map.addInteraction(interactionSelect);
 interaction = new ol.interaction.Modify({
 features: interactionSelect.getFeatures()
 });
 map.addInteraction(interaction);
 map.addInteraction(interactionSnap);
 dirty = {};
 interactionSelect.getFeatures().on('add', function (e) {
 e.element.on('change', function (e) {
 dirty[e.target.getId()] = true;
 });
 });
 interactionSelect.getFeatures().on('remove', function (e) {
 var f = e.element;
 if (dirty[f.getId()]) {
 delete dirty[f.getId()];
 var featureProperties = f.getProperties();
 delete featureProperties.boundedBy;
 var clone = new ol.Feature(featureProperties);
 clone.setId(f.getId());
 transactWFS('update', clone);
 }
 });
 break;
 case 'btnPoint':
 
 interaction = new ol.interaction.Draw({
 // type: 'Point',
 source: layerWFS.getSource(),
 geometryName: 'geometry'
 });
 map.addInteraction(interaction);
 interaction.on('drawend', function (e) { 
 
 var comentario = document.getElementById('comentario').value;
 var puntaje = document.getElementById('puntaje').value;
 var pointGid = pointSelect.get("gid");
 e.feature.set('comentario', comentario);
 e.feature.set('puntaje', puntaje);
 e.feature.set('poi_gid',pointGid);
 transactWFS('insert', e.feature);
 });
 break;
 case 'btnLine':
 interaction = new ol.interaction.Draw({
 type: 'LineString',
 source: layerWFS.getSource()
 });
 map.addInteraction(interaction);
 interaction.on('drawend', function (e) {                    
 transactWFS('insert', e.feature);
 });
 break;
 case 'btnArea':
 interaction = new ol.interaction.Draw({
 type: 'Polygon',
 source: layerWFS.getSource()
 });
 interaction.on('drawend', function (e) {
 transactWFS('insert', e.feature);
 });
 map.addInteraction(interaction);
 break;
 case 'btnDelete':
 interaction = new ol.interaction.Select();
 interaction.getFeatures().on('add', function (e) {
 
 transactWFS('delete', e.target.item(0));
 interactionSelectPointerMove.getFeatures().clear();
 interaction.getFeatures().clear();
 });
 map.addInteraction(interaction);
 break;
 default:
 break;
 }
 });*/


