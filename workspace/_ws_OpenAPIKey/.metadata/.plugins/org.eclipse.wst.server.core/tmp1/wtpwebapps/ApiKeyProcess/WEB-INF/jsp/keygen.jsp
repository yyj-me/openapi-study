<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>키 생성 화면!</h1>
	<hr />
	<form method="post" action="makeKey">
		<input type="text" id="hostName" name="hostName" value="http://localhost:8000" style="width:400px;"/>	
		<br>
		<input type="submit" value="키 생성" />
	</form> 
</body>
</html>