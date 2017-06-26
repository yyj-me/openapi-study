<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.multi.contacts.biz.domain.*" %>
<%
	List<Contact> contacts = (List<Contact>)request.getAttribute("data");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>연락처 관리 기능 테스트</title>
<script type="text/javascript">
	window.onload=function() {
		var f1 = document.getElementById("f1");
		var no = document.getElementById("no");
		var add = document.getElementById("add");
		var update = document.getElementById("update");
		
		add.onclick = function() {
			no.value = "0";
			f1.submit();
		};
		update.onclick = function() {
			f1.action = "update.do";
			f1.submit();
		};		
	}
</script>
</head>
<body>
	<div style="border:solid 1px gray; padding:5px; margin:10px">
		<form id="f1" method="POST" action="add.do">
			번호:<input id="no" type="text" name="no" value="0"/><br/>
			이름:<input type="text" name="name" /><br/>
			전번:<input type="text" name="tel" /><br/>
			메일:<input type="text" name="email" /><br/>
			<input id="add" type="button" value="추가"/>
			<input id="update" type="button" value="수정"/>
		</form>
	</div>	
	<div>
		<% for(Contact c: contacts) { %>
		<div>
		<%=c.getNo()%>:<%=c.getName()%>,<%=c.getTel()%>,<%=c.getEmail()%>
		</div>
		<% } %>
	</div>

</body>
</html>