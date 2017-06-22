<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="description" content="description"/>
<meta name="keywords" content="keywords"/> 
<meta name="author" content="author"/> 
<link rel="stylesheet" type="text/css" href="../default.css"/>
<title>Blackbox</title>
</head>
<body>
<div class="main">
	<div class="gfx"><h1>Terminator Factory</h1></div>
	<jsp:include page="include/menu.jsp" />
	<div class="content">
		<div class="item">
			<h1>Regist New Terminator Model </h1>
			
			<form id="f1" method="post" enctype="multipart/form-data">
			<table width="100%" >
			  <tr>
			    <td>Model Code </td>
			    <td><input type="text" name="mcode" value="T1000"></input></td> 
			  </tr>
			  <tr>
			    <td>Model Name </td>
			    <td><input type="text" name="mname" value="T1000 Model"></input></td> 
			  </tr>
			  <tr>
			    <td>Model Description </td>
			    <td><textarea name="mdesc" cols="50"  rows="10">The T-1000 is a fictional nanomorph mimetic poly-alloy (liquid metal) assassin and the main antagonist in Terminator 2: Judgment Day.[1] The T-1000 is portrayed primarily by Robert Patrick; however, being a shape-shifter, the T-1000 is played by other actors in some scenes of the film. In Terminator 2, the T-1000 is presented as a technological leap over the "800 Series" Terminator (Arnold Schwarzenegger).[2] Described by Allmovie as "one of the most memorable roles in one of the most memorable films of the decade",[3] Patrick's portrayal of the T-1000 earned him nominations for Best Villain and Best Supporting Actor at the 1992 MTV and Saturn Awards[4] and was ranked #39 in the Online Film Critics Society's "Top 100 Villains of All Time" in 2002    
			        </textarea></td> 
			  </tr>			  			
			  <tr>
			    <td>Model Image </td>
			    <td><input type="file" name="uploadFile"></input></td> 
			  </tr>
			  <tr>
			    <td> </td>
			    <td> <input type="submit" value="regist"></input> <input type="reset" ></input>   </td> 
			  </tr>			  			
			</table>
			</form>
		</div>
	</div>
	<div class="footer">&copy; 2006 <a href="index.html">Sitename.com</a>. Design by <a href="http://arcsin.se">Arcsin</a></div>
</div>
</body>
</html>