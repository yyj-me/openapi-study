<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="description" content="_your description goes here_" />
<meta name="keywords" content="_your,keywords,goes,here_" />
<meta name="author" content="_your name goes here_  / Original design: Andreas Viklund - http://andreasviklund.com/" />
<link rel="stylesheet" type="text/css" href="andreas05.css" />
<title>Sara And John</title>
<style>

.layoutYoutube {
  padding-left:30px;
  magin:20px;
  width:400px;
  height:699px;
  left: 120px; 
  top: 90px; 
  position: absolute; 
  visibility: visible; 
  clip: rect(auto auto auto auto); 
  overflow-x: visible; overflow-y: visible;
  background-color: white;
}
</style>


<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://swfobject.googlecode.com/svn/trunk/swfobject/swfobject.js"></script>
<script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=11cce16c2a03b0daec91a201c68b99f7"></script>

<script type="text/javascript" src="js/jsr_class.js"></script>
<script type="text/javascript" src="js/youtubeutil.js"></script>
<script type="text/javascript">


var groupOverlay;
var interval;
var oLabel;

jQuery(document).ready(
	function () {
		interval = setInterval(loadTerminatorLocations,3000);		

		jQuery(".layoutYoutube").hide();
		loadMap();
		jQuery("layout:last").addClass("youtubeDiv");
		
		jQuery('#findModels').click(function(){
			interval = setInterval(loadTerminatorLocations,3000);
			jQuery(".layoutYoutube").show(500);
			findYouTube();
		});
		
		jQuery('#stopMap').click(function(){
			clearInterval(interval);
		});
		
		jQuery('#searchModel').click(function(){
		   
			findYouTube();
		});
		
		jQuery('#closeYoutube').click(function(){

			jQuery(".layoutYoutube").hide(1000);
		});
		
	}
);


function loadTerminatorLocations(){
	
	groupOverlay.clearOverlay();
	oMap.clearOverlay();

	jQuery.getJSON('http://satellite.com:8000/satellite/pos/all?callback=?', function(data){
		
		jQuery(data).each(function(index){
			
			var oMarker1 = new nhn.api.map.Marker(oIcon,  { title : this.tcode});
			oMarker1.setPoint(new nhn.api.map.LatLng(this.latitude, this.longitude));
			groupOverlay.addOverlay(oMarker1);
		});
			
	});
	
	
	oMap.addOverlay(groupOverlay);
	
	
}

function loadMap(){
	
	groupOverlay = new nhn.api.map.GroupOverlay();
	

	var oCenterPoint = new nhn.api.map.LatLng(37.5010226, 127.0396037);
	
	nhn.api.map.setDefaultPoint('LatLng');
	oMap = new nhn.api.map.Map('testMap', {
				point : oCenterPoint,
				zoom : 7, // - 초기 줌 레벨은 7
				enableWheelZoom : false,
				enableDragPan : true,
				enableDblClickZoom : false,
				mapMode : 0,
				activateTrafficMap : true,
				activateBicycleMap : true,
				minMaxLevel : [ 3, 20 ],
				size : new nhn.api.map.Size(400, 400)
	});
	
	var mapZoom = new nhn.api.map.ZoomControl(); // - 줌 컨트롤 선언
	mapZoom.setPosition({left:40, bottom:40}); // - 줌 컨트롤 위치 지정
	oMap.addControl(mapZoom); // - 줌 컨트롤 추가.
	
	var oSize = new nhn.api.map.Size(42, 56);
	var oOffset = new nhn.api.map.Size(14, 37);
	
	oIcon = new nhn.api.map.Icon('terminator_cyborg.gif', oSize, oOffset);
	
	groupOverlay.attach('click', function(oEvent) {
 		jQuery('#modelNo').val(oEvent.target.getTitle());
		jQuery(".layoutYoutube").show(500);
		findYouTube();
	});
	
	groupOverlay.attach('mouseenter', function(oEvent) {
		if (oEvent.target instanceof nhn.api.map.Marker) {
			var oMarker = oEvent.target;
			
			oLabel = new nhn.api.map.MarkerLabel(); 
			oMap.addOverlay(oLabel); 
			oLabel.setVisible(true, oMarker); 		
		}
	});

	groupOverlay.attach('mouseleave', function(oEvent) {
		if (oEvent.target instanceof nhn.api.map.Marker) {
			oLabel.setVisible(false);
		}
	});

}
</script>
</head>

<body>
<div id="title"><h1>John connor's Site</h1></div>
<div id="container">
<div id="sidebar">

<h2>Site menu</h2>
<a class="menu" href="main.do">Main page</a>
<a class="menu" href="viewModel2.do">Connect TFactory</a>
<a class="menu" href="viewPosition.do">Connect Satellite</a>
<a class="menu" href="viewMap.do">MashUp Example</a>
<a class="menu" href="#">Sample</a>
<a class="menu" href="#">Sample</a>
<a class="menu" href="#">Sample</a>
<a class="menu" href="#">Sample</a>
</div>

<div id="main">
 <div class="layout" id="mapDiv">
  <h2 id = "findModels">Find All Terminators</h2>
  <div id = "testMap" style="border:1px solid #000; width:350px; height:380px; "></div>
  <span id ="stopMap">STOPMAP</span>
 </div> 
 
 
 <div class="layoutYoutube" >
  <div id = "youtubeDiv" >YOUTUBE SEARCH  <input type="text" id="modelNo" name="modelNo" value="T-800"/> <input type="button" id="searchModel" value="searchModel" /> <span id="closeYoutube">CLOSE</span></div>

	<div id="playerContainer" style="width: 20em; height: 200px;">
    	<object id="player"></object>
	</div><br/>
	<div id="videos2" style="width:500"></div>
 </div>  

<p>
<div id="fb-root"></div><script src="http://connect.facebook.net/ko_KR/all.js#appId=212993078744533&amp;xfbml=1"></script><fb:comments href="localhost:8000/snj/viewMap.do" num_posts="2" width="400"></fb:comments>      
</p>

<h2>An open source design!</h2>
<p>This design is released as open source, which means that you can make any changes you may want to, and use the page in any way you want to. Have fun! If you want more designs to choose from, you can visit <a href="http://oswd.org/userinfo.phtml?user=Andreas">my page</a> at OSWD.org, or download my other designs directly: <a href="http://www.oswd.org/download.phtml/andreas01.zip?id=2199">01</a> |
<a href="http://www.oswd.org/download.phtml/andreas02.zip?id=2204">02</a> |
<a href="http://www.oswd.org/download.phtml/andreas03.zip?id=2340">03</a> |
<a href="http://www.oswd.org/download.phtml/andreas04.zip?id=2346">04</a></p>

<p class="credits">&copy; 2005 Your name | Design by <a href="http://andreasviklund.com">Andreas Viklund</a></p>
</div>

<div id="footer"></div>
</div>
</body>
</html>