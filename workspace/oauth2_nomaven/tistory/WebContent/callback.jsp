<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.multi.tistorytest.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.httpclient.*" %>
<%@ page import="org.apache.commons.httpclient.methods.*"%>
<%
	//http://localhost:8000/tistory/callback.jsp?code=f89e9f48f02ffb80b83f33c5970b9557625ab1c77bf45fbdd3304f04734a4770716df952
 	//code 파라미터는 verification 값임. 사용자가 승인했음을 나타냄.
	String code = request.getParameter("code");
	HashMap<String, String> map = new HashMap<String,String>();
	map.put("client_id", Settings.CLIENT_ID);
	map.put("client_secret", Settings.CLIENT_KEY);
	map.put("redirect_uri", Settings.REDIRECT_URI);
	map.put("grant_type", "authorization_code");
	map.put("code", code);
	
	String url = Settings.ACCES_TOKEN_URL + "?" + Settings.getParamString(map);
	
	HttpClient client = new HttpClient();
	client.getParams().setContentCharset("utf-8");
	GetMethod method = new GetMethod(url);
	int status = client.executeMethod(method);
	String result= "";
	if (status == 200) {
		String body = method.getResponseBodyAsString();
		String[] temp = body.split("=");
		if (temp != null && temp[0].equals("access_token")) {
			session.setAttribute("access_token", temp[1]);
			response.sendRedirect("main.jsp");
		}
	} else {
		result = "인증 실패!!";
	}
%>
<%=result %>
