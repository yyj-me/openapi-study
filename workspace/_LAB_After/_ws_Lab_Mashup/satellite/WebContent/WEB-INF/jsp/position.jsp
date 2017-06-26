<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%
	String json = (String)request.getAttribute("jsonData");
    //response.setHeader("Access-Control-Allow-Origin","*");
    //response.setHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS, PUT, DELETE");
    //response.setHeader("Access-Control-Max-Age","86400");
%><%=json %>
