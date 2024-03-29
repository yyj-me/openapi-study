<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en_US" xml:lang="en_US">
 <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<title>OpenAPI Map Test - 마커, InfoWindow 테스트</title>
		<!-- prevent IE6 flickering -->
		<script type="text/javascript">
			try {document.execCommand('BackgroundImageCache', false, true);} catch(e) {}
		</script>

<script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=163136c5b011e46df5e70422988b110c"></script>
 <body>
 <div id = "testMap" style="border:1px solid #000; width:500px; height:400px; margin:20px;"></div>

 <script type="text/javascript">
			var oCenterPoint = new nhn.api.map.LatLng(37.5010226, 127.0396037);
			nhn.api.map.setDefaultPoint('LatLng');
			oMap = new nhn.api.map.Map('testMap', {
						point : oCenterPoint,
						zoom : 10, // - 초기 줌 레벨은 10으로 둔다.
						enableWheelZoom : false,
						enableDragPan : true,
						enableDblClickZoom : false,
						mapMode : 0,
						activateTrafficMap : false,
						activateBicycleMap : false,
						minMaxLevel : [ 5, 12 ],
						size : new nhn.api.map.Size(500, 400)
			});
			var markerCount = 0;
			
			var oSize = new nhn.api.map.Size(28, 37);
			var oOffset = new nhn.api.map.Size(14, 37);
			var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);
			
			var mapInfoTestWindow = new nhn.api.map.InfoWindow(); // - info window 생성
			mapInfoTestWindow.setVisible(false); // - infowindow 표시 여부 지정.
			oMap.addOverlay(mapInfoTestWindow);	// - 지도에 추가.	 
			
			var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
			oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.

			mapInfoTestWindow.attach('changeVisible', function(oCustomEvent) {
				if (oCustomEvent.visible) {
					oLabel.setVisible(false);
				}
			});
	
	
			oMap.attach('mouseenter', function(oCustomEvent) {
				var oTarget = oCustomEvent.target;
				// 마커위에 마우스 올라간거면
				if (oTarget instanceof nhn.api.map.Marker) {
					var oMarker = oTarget;
					oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다.
				}
			});
	
			oMap.attach('mouseleave', function(oCustomEvent) {
				var oTarget = oCustomEvent.target;
				// 마커위에서 마우스 나간거면
				if (oTarget instanceof nhn.api.map.Marker) {
					oLabel.setVisible(false);
				}
			});
	
			oMap.attach('click', function(oCustomEvent) {
				var oPoint = oCustomEvent.point;
				var oTarget = oCustomEvent.target;
				mapInfoTestWindow.setVisible(false);
				// 마커 클릭하면
				if (oTarget instanceof nhn.api.map.Marker) {
					// 겹침 마커 클릭한거면
					if (oCustomEvent.clickCoveredMarker) {
						return;
					}
					// - InfoWindow 에 들어갈 내용은 setContent 로 자유롭게 넣을 수 있습니다. 외부 css를 이용할 수 있으며, 
					// - 외부 css에 선언된 class를 이용하면 해당 class의 스타일을 바로 적용할 수 있습니다.
					// - 단, DIV 의 position style 은 absolute 가 되면 안되며, 
					// - absolute 의 경우 autoPosition 이 동작하지 않습니다. 
					mapInfoTestWindow.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
					'<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 2px 2px 2px !important">' + 
					'Hello World <br /> ' + oTarget.getPoint()
					+'<span></div>');
					mapInfoTestWindow.setPoint(oTarget.getPoint());
					mapInfoTestWindow.setVisible(true);
					mapInfoTestWindow.setPosition({right : 15, top : 30});
					mapInfoTestWindow.autoPosition();
					return;
				}
				var oMarker = new nhn.api.map.Marker(oIcon, { title : '마커 : ' + oPoint.toString() });
				oMarker.setPoint(oPoint);
				oMap.addOverlay(oMarker);
			});
			
</script>
</body>
</html>
    