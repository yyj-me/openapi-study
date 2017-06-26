<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	if (session.getAttribute("access_token") == null)
		response.sendRedirect("index.jsp");	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$.get("bloginfo.jsp", function(json) {
		console.log(json);
		$("#blogger").html(json.tistory.item[0].nickname);
		$("#url").html(json.tistory.item[0].url);
	});
});
</script>
</head>
<body>
	<h1>블로그 정보</h1>
	블로그 작성자 : <span id="blogger"></span><br>
	블로그 URL : <span id="url"></span><br>
	Access Token : <%=session.getAttribute("access_token") %>
</body>
</html>