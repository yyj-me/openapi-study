<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.multi.oauth2client.*" %>
<%@ page import="java.util.*" %>
<%
	HashMap<String,String> map = new HashMap<String, String>();
	map.put("client_id", Settings.CLIENT_ID);
	map.put("redirect_uri", Settings.REDIRECT_URI);
	map.put("response_type", "code");
	//callback쪽에서 값의 일치 여부 확인
	session.setAttribute("state", map.get("state"));
	
	String url = Settings.AUTHORIZE_URL + "?" + Settings.getParamString(map, false);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>daum_serverflow</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
<a href="<%=url %>">Authorize!!</a>
</body>
</html>