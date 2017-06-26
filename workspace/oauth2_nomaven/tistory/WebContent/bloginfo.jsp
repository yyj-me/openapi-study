<%@ page language="java" contentType="application/json; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.multi.tistorytest.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.httpclient.*" %>
<%@ page import="org.apache.commons.httpclient.methods.*"%>
<%
	if (session.getAttribute("access_token") == null)
		response.sendRedirect("index.jsp");

	String access_token = (String)session.getAttribute("access_token");
	HashMap<String, String> map = new HashMap<String,String>();
	map.put("output", "json");
	map.put("access_token", access_token);
	String url = Settings.BLOG_INFO_URL + "?" + Settings.getParamString(map);
	
	HttpClient client = new HttpClient();
	client.getParams().setContentCharset("utf-8");
	GetMethod method = new GetMethod(url);
	int status = client.executeMethod(method);
	String result = "";
	
	if (status == 200) {
		result = method.getResponseBodyAsString();	
	} else {
		result = "Error!";
	}
%><%=result %>