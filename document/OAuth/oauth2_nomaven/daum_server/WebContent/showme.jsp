<%@ page language="java" contentType="application/json; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.multi.oauth2client.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.httpclient.*" %>
<%@ page import="org.apache.commons.httpclient.methods.*"%>
<%
	if (session.getAttribute("access_token") == null)
		response.sendRedirect("index.jsp");

	AccessTokenVO tokenVO = (AccessTokenVO)session.getAttribute("access_token");
	String access_token = tokenVO.getAccess_token();
	HashMap<String, String> map = new HashMap<String,String>();
	map.put("access_token", tokenVO.getAccess_token());
	String url = Settings.PERSONAL_INFO_URL + "?" + Settings.getParamString(map, false);
	
	HttpClient client = new HttpClient();
	client.getParams().setContentCharset("utf-8");
	GetMethod method = new GetMethod(url);
	int status = client.executeMethod(method);

	String result = "";
	if (status == 200) {
		result = method.getResponseBodyAsString();	
	}
%><%=result %>