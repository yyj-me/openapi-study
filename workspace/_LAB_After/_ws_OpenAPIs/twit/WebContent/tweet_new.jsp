<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=utf-8" />
<title>트윗 작성하기</title>
</head>
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/urlEncodeUtil.js"></script>
<script type="text/javascript">
var baseUrl = "update";

function tweetNew() {
	var status = $("#status").val();
	//$("#temp").text(status);
	//alert();
	//alert(encodeURIComponent(status));
	$.ajax({
        type: "get",
        data : { "status" : status },
      	url: baseUrl,
        dataType: "json",
        success: function (json) {
        	alert('트윗 작성 성공');
        	//json 데이터 파싱하여 에러/성공 여부 확인해야 함.
        }
    });
}

</script>
<body>
	<h1>트윗 작성하기</h1>
	<hr/>
	<div id="temp"></div><br/><br/>
	<form id="f1" method="post" action="">
	<textarea id="status" name="status" style="width:400px; height:100px;"></textarea>
	</form>
	<br/><br/>
	<input type="button" value="작  성"  onclick="tweetNew()" />
</body>
</html>