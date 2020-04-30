/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Capas para dibujar features
var vectorSource = new ol.source.Vector();
var vector = new ol.layer.Vector({
    visible: true,
    source: vectorSource,
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

//Funciones para las features
var poiCollection = new ol.Collection();
var modify = new ol.interaction.Modify({source: vectorSource});
map.addInteraction(modify);

var draw, snap; // global so we can remove them later
var typeSelect = document.getElementById('type');

function addInteractions() {
    draw = new ol.interaction.Draw({
        features:poiCollection,
        source: vectorSource,
        type: typeSelect.value
    });
    poiCollection.push(draw);
    //addPointToLayer(draw);
    map.addInteraction(draw);
    snap = new ol.interaction.Snap({source: vectorSource});
    map.addInteraction(snap);

}

/**
 * Handle change event.
 */

typeSelect.onchange = function () {
    map.removeInteraction(draw);
    map.removeInteraction(snap);
    addInteractions();
};

addInteractions();
function addPointToLayer(draw)
{
    var node = formatWFS.writeTransaction(draw, null, null, {
        featureNS: 'CapasPostgis',
        featurePrefix: 'CapasPostgis',
        featureType: 'pois',
        srsName: 'EPSG:32721',
        gmlOptions: formatGML
    });

}

export {vector};