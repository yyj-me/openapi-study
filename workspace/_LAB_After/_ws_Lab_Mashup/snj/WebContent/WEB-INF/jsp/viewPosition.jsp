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
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript">

function doJob(){
	
	var mcode = $("#mcode").val();
	
	alert(mcode);
	
	if(mcode != 'ALL'){
	
	 	$.getJSON("http://satellite.com:8000/satellite/pos/"+mcode +"?callback=?", function(data){
			alert(data.terminatorGeo.tcode+":"+data.terminatorGeo.latitude+":"+data.terminatorGeo.longitude);
			$("#posdiv").text(data.terminatorGeo.tcode+":"+data.terminatorGeo.latitude+":"+data.terminatorGeo.longitude);
			
		}
		); 
	}else {
		
		alert("get All models data");
		
		$.getJSON('http://satellite.com:8000/satellite/pos/all?callback=?', function(data){
			$.each(response.terminatorGeoList,function(i, data){ 
				$("#posdiv").append(data.tcode+":"+data.latitude+":"+data.longitude +"<br>");
			}
			);//end each
			
		}
		);
		
	}	
	
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
<h2>Insert Terminator code !!! </h2>
<p>Model Code : <input type="text" id="mcode" value="T1"></input><a href="javascript:doJob();">FIND</a></p>
<p>
<div id="posdiv"></div>
</p>




<p class="credits">&copy; 2005 Your name | Design by <a href="http://andreasviklund.com">Andreas Viklund</a></p>
</div>

<div id="footer"></div>
</div>
</body>
</html>