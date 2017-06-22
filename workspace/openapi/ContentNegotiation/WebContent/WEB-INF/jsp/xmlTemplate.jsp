<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sds.hr.vo.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	CountryList countryList = (CountryList)request.getAttribute("list");
	List<Country> list = countryList.getCountries();
%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>xmlTemplate.jsp</h1>
	<hr/>
	---------------------------------------<br/>		
	국가코드, 국가명, 지역<br/>
	---------------------------------------<br/>	
	<% for(int i=0; i < list.size(); i++) { 
			Country vo = list.get(i);	%>
		<%=vo.getCountry_id() %>, 
		<%=vo.getCountry_name() %>, 
		<%=vo.getRegion_name() %><br/>
	<%  } %>
	---------------------------------------<br/>		
</body>
</html>