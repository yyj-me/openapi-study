<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
You are free to copy and use this sample in accordance with the terms of the
Apache license (http://www.apache.org/licenses/LICENSE-2.0.html)
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Google Earth API Sample</title>
    <script src="http://www.google.com/jsapi?key=ABQIAAAAuPsJpk3MBtDpJ4G8cqBnjRRaGTYH6UMl8mADNa0YKuWNNa8VNxQCzVBXTx2DYyXGsTOxpWhvIG7Djw" type="text/javascript"></script>
    <script type="text/javascript">
    var ge;
    
    google.load("earth", "1");
    
    function init() {
      google.earth.createInstance('map3d', initCallback, failureCallback);
    }
    
    function initCallback(instance) {
      ge = instance;
      ge.getWindow().setVisibility(true);
    
      // add a navigation control
      ge.getNavigationControl().setVisibility(ge.VISIBILITY_AUTO);
    
      // add some layers
      ge.getLayerRoot().enableLayerById(ge.LAYER_BORDERS, true);
      ge.getLayerRoot().enableLayerById(ge.LAYER_ROADS, true);
    
      // Sample KML taken from
      //   http://code.google.com/apis/kml/documentation/kml_tut.html#polygons
      var timeAwareDoc = ge.parseKml(
        '<?xml version="1.0" encoding="UTF-8"?>' +
        '<kml xmlns="http://www.opengis.net/kml/2.2">' +
        '  <Document>' +
        '    <Placemark>' +
        '      <name>Placemark 1</name>' +
        '      <TimeSpan>' +
        '        <begin>2007-01-14T21:05:02Z</begin>' +
        '        <end>2007-01-14T21:05:20Z</end>' +
        '      </TimeSpan>' +
        '      <Point>' +
        '        <coordinates>-122.536226,37.86047,0</coordinates>' +
        '      </Point>' +
        '    </Placemark>' +
        '    <Placemark>' +
        '      <name>Placemark 2</name>' +
        '      <TimeSpan>' +
        '        <begin>2007-01-14T21:05:20Z</begin>' +
        '        <end>2007-01-14T21:05:43Z</end>' +
        '      </TimeSpan>' +
        '      <Point>' +
        '        <coordinates>-122.536422,37.860303,0</coordinates>' +
        '      </Point>' +
        '    </Placemark>' +
        '    <Placemark>' +
        '      <name>Placemark 3</name>' +
        '      <TimeSpan>' +
        '        <begin>2007-01-14T21:05:43Z</begin>' +
        '        <end>2007-01-14T21:06:04Z</end>' +
        '      </TimeSpan>' +
        '      <Point>' +
        '        <coordinates>-122.536688,37.860072,0</coordinates>' +
        '      </Point>' +
        '    </Placemark>' +
        '  </Document>' +
        '</kml>');
    
      ge.getFeatures().appendChild(timeAwareDoc);
    
      // Fly to the Pentagon
      var la = ge.createLookAt('');
      la.set(37.860303, -122.536422, 0, ge.ALTITUDE_RELATIVE_TO_GROUND, 0, 45, 75);
      ge.getView().setAbstractView(la);
    
      document.getElementById('installed-plugin-version').innerHTML =
        ge.getPluginVersion().toString();
    }
    
    function failureCallback(errorCode) {
    }
    
    </script>
  </head>
  <body onload="init()" style="font-family: arial, sans-serif; font-size: 13px; border: 0;">
    <div id="map3d" style="width: 500px; height: 380px;"></div>
    <br>
    <div>Installed Plugin Version: <span id="installed-plugin-version" style="font-weight: bold;">Loading...</span></div>
  </body>
