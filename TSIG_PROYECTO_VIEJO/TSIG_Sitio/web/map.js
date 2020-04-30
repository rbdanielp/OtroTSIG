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

var supportsFiltering = true;
if (!supportsFiltering) {
    document.getElementById('filter').disabled = true;
    document.getElementById('updateFilterButton').disabled = true;
    document.getElementById('resetFilterButton').disabled = true;
}
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
var filter = '', poiType = '';
var sourceWFS = new ol.source.Vector({
    format: new ol.format.GeoJSON(),
    strategy: ol.loadingstrategy.bbox,
    projection: projection    
});
var layerWFS = new ol.layer.Vector();
//filter = '&Filter=%3CFilter%3E%3CPropertyIsEqualTo%3E%3CPropertyName%3Edtype%3C/PropertyName%3E%3CLiteral%3E' + poiType + '%3C/Literal%3E%3C/PropertyIsEqualTo%3E%3C/Filter%3E';

function cargarPois() {
    sourceWFS.clear();
        $.ajax('http://localhost:8085/geoserver/wfs?service=WFS&version=1.1.0&request=GetFeature&outputFormat=application/json&typenames=capatsig:pois&featureNS=www.capatsig.uy&featureType=poi&srsname=EPSG:32721&geometryName=geometry&bound=567000,+6135000,+585000,+6145000' + filter
                ).done(function (response) {
            sourceWFS.addFeatures(sourceWFS.getFormat().readFeatures(response));
        });
 };
 
cargarPois();
layerWFS = new ol.layer.Vector({
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

var comentarios;
//http://localhost:8085/geoserver/wfs?service=WFS&version=1.1.0&request=GetFeature&outputFormat=application/json&typenames=capatsig:comentario&featureNS=www.capatsig.uy
var comentarios = new ol.source.Vector({
    format: new ol.format.GeoJSON(),
    loader: function (extent) {
        $.ajax('http://localhost:8080/geoserver/wfs', {
            type: 'POST',
            data: {

                service: 'WFS',
                version: '1.1.0',
                request: 'GetFeature',
                outputFormat: 'application/json',
                typenames: 'CapasPostgis:comentario',
                featureNS: 'CapasPostgis',
            }
        }).done(function (response) {
            comentarios.addFeatures(comentarios.getFormat().readFeatures(response));
        });
    },
});

console.log(comentarios);

//****************************************Promociones********************************************
function mostrarPois(){
    if(document.getElementById('promociones').checked)
        sourcePromoWFS.setVisible=true;
    else
        sourcePromoWFS.setVisible=false;
}

var sourcePromoWFS = new ol.source.Vector({
    format: new ol.format.GeoJSON(),
    loader: function (extent) {
        $.ajax('http://localhost:8085/geoserver/wfs', {
            type: 'POST',
            data: {
                service: 'WFS',
                version: '1.1.0',
                request: 'GetFeature',
                outputFormat: 'application/json',
                typenames: 'capatsig:promocion',
                featureNS: 'capatsig',
                featureType: 'promocion',
                srsname: 'EPSG:32721',
                geometryName: 'geometry',
                bound: bounds,
            }
        }).done(function (response) {
            sourceWFS.addFeatures(sourceWFS.getFormat().readFeatures(response));
        });
    },
    strategy: ol.loadingstrategy.bbox,
    projection: projection
});

var layerPromoWFS = new ol.layer.Vector({
    visible: true,
    source: sourcePromoWFS,
    style: new ol.style.Style({
        fill: new ol.style.Fill({
            color: 'rgba(255, 255, 255, 0.2)'
        }),
        stroke: new ol.style.Stroke({
            color: '#ffcc33',
            width: 2
        }),
        image: new ol.style.Circle({
            radius: 7,
            fill: new ol.style.Fill({
                color: '#ffcc33'
            })
        })
    })
});

//****************************************Promociones********************************************

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
        layerPromoWFS
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

function filters() {
    poiType = document.getElementById('filter').value;
    filter = '&Filter=%3CFilter%3E%3CPropertyIsEqualTo%3E%3CPropertyName%3Edtype%3C/PropertyName%3E%3CLiteral%3E' + poiType + '%3C/Literal%3E%3C/PropertyIsEqualTo%3E%3C/Filter%3E';
    cargarPois();
}


function resetFilter() {
    document.getElementById('filter').value = "";
    filter="";
    cargarPois();
}

// shows/hide the control panel
function toggleControlPanel() {
    var toolbar = document.getElementById("toolbar");
    if (toolbar.style.display == "none") {
        toolbar.style.display = "block";
    } else {
        toolbar.style.display = "none";
    }
    map.updateSize()
}
