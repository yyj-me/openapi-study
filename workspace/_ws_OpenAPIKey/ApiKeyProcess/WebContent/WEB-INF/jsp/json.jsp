<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%
	String json = (String)request.getAttribute("jsonData");
    response.setHeader("Access-Control-Allow-Origin","*");
%><%=json %>
