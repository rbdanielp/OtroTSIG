/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var myWFSRequest = new StringBuffer();
myWFSRequest.append("<wfs:Transaction xmlns:wfs='http://www.opengis.net/wfs' service='WFS' version='1.0.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.opengis.net/wfs http://localhost:8080/geoserver/schemas/wfs/1.0.0/WFS-basic.xsd CapasPostgis http://localhost:8080/geoserver/CapasPostgis/wfs/DescribeFeatureType?typeName=CapasPostgis%3Apois'>");
myWFSRequest.append("<wfs:Insert>");
myWFSRequest.append("<CapasPostgis:pois xmlns:feature='CapasPostgis'>");
myWFSRequest.append("<CapasPostgis:geom>");
myWFSRequest.append("<gml:point xmlns:gml='http://www.opengis.net/gml' srsName='http://www.opengis.net/gml/srs/epsg.xml#32721'>");
myWFSRequest.append("<gml:coordinates decimal='.' cs=',' ts=' '>572177.84813, 6139774.09803 </gml:coordinates>");
myWFSRequest.append("</gml:point>");
myWFSRequest.append("</CapasPostgis:geom>");
myWFSRequest.append("<CapasPostgis:id>15</CapasPostgis:id>");
myWFSRequest.append("<CapasPostgis:nombre>prueba</CapasPostgis:nombre>");
myWFSRequest.append("<CapasPostgis:dtype>Estadio</CapasPostgis:dtype>");
myWFSRequest.append("</CapasPostgis:pois>");
myWFSRequest.append("</wfs:Insert>");
myWFSRequest.append("</wfs:Transaction>");  
 
var myPost = new PostMethod("http://localhost:8080/geoserver/CapasPostgis/wfs");
myPost.setRequestBody(myWFSRequest.toString());
var theClient= new HttpClient();
try {
    theClient.executeMethod(myPost);
    var responseString = myPost.getResponseBodyAsString();
    console.error(responseString);
    console.log.debug("no town BOSTON found in TOWNS_POLY SDE layer");
    
} catch (ioe) {
    var rte = new RuntimeException(ioe);
    rte.setStackTrace(ioe.getStackTrace());
    throw rte;
}
