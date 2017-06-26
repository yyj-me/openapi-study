<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--this design was created by Vacant (Chris Blunden), http://www.web-site.tk .              
 Deep, version 1.5 created on 17/10/04 for OSWD.org ... DO NOT REMOVE this notice. Thanks.-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		
		<title>Design by Vacant</title>
		
		<link rel="stylesheet" type="text/css" href="../style.css" />
		<!-- This page fully validates as XHTML 1.0 Strict on validator.w3.org (notice can be removed) -->
		<script type="text/javascript">
		  
		function viewPos(){
			
			var mcode = document.getElementById("mcode").value;

			if (mcode == "ALL")
				mcode = "all";
				
			self.location=""+ mcode;
		}
		
		</script>

	</head>

	<body>
		<div id="outer">
			<div id="inner">
			
			<div class="bgtext">Satellite</div>
			
			
			<div id="text1"><a href="home.html">home<span>&raquo;the main bit, where stuff happens.</span></a></div>
			<div id="text2"><a href="#">stuff<span>&raquo;wow, that titles pretty large. you can change all of that in the stylesheet.</span></a></div>
			<div id="text3"><a href="#">archives<span>&raquo;It's all done in CSS, too.</span></a></div>
			<div id="text4"><a href="#">music<span>&raquo;put another thing here</span></a></div>
			<div id="text5">CODE NO: <input type="text" id="mcode"></input><a href="javascript:viewPos();">VIEW</a><span>&raquo;sub text. you can put it here.</span></div>
			<div id="text6"><a href="#">opinion time<span>&raquo;isnt it nice how this text just appears?</span></a></div>
			<div id="text7"><a href="mailto:froggyb[at]gmail[dot]com">contact us<span>&raquo;complain or make suggestions here</span></a></div>			
			
			</div>
		</div>
		
	</body>

</html>