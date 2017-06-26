<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.multi.tistorytest.*" %>
<%@ page import="java.util.*" %>
<%
	HashMap<String,String> map = new HashMap<String, String>();
	map.put("client_id", Settings.CLIENT_ID);
	map.put("redirect_uri", Settings.REDIRECT_URI);
	map.put("response_type", "code");
	String url = Settings.AUTHORIZE_URL + "?" + Settings.getParamString(map);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>tistory oauth test</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
http://localhost:8000/tistory<br>
서비스 형태 :	웹서비스<br>
서비스 권한	: 읽기/쓰기<br>
CallBack 경로 :	http://localhost:8000/tistory/callback.jsp<br>
Client ID	: 8e59b1061b9ad359788565442498c447<br>
Secret Key	: 586a35ef3eb8d11733a3da95b4af2b059da4dad1a79e08cf9b8344377b0cceaf2daf431d<br>
Authorize URL : https://www.tistory.com/oauth/authorize<br>
Access Token URL : https://www.tistory.com/oauth/access_token<br><br>

<a href="<%=url %>">Authorize Tistory!!</a>
</body>
</html>