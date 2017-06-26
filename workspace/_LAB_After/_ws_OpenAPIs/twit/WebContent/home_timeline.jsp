<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<script type="text/javascript">
	var page= 1;
	var count = 3;
	var baseUrl = "getHomeTimeLine";
	
	window.onload = getTimeline;
	
	function go(num) {
		page += num;
		getTimeline();
	}	
	
	function getTimeline() {
		var url = baseUrl + "?page=" + page + "&count=" + count;
		
		$.ajax({
	        type: "GET",
	      	url: url,
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
	        error: function(xhr, status, error) {
	            alert("error : " +status);
	        },
	        success: function (json) {
	        	displayTimeline(json);
	        }
	    });
	}
	
	function displayTimeline(json) {
		$("#divTimeline").html("");
		for (var i=0; i < json.length; i++) {
			var tweet = json[i];
			var div = $("<div />");
			div.append("<span>" + tweet.user.name + "(" + tweet.user.screen_name + ") --- " + tweet.id + " </span><br/>");
			div.append(tweet.text + "<br/><br/>");
			div.append("<span>" + tweet.created_at + "</span><hr/>");
			$("#divTimeline").append(div);
			max_id = tweet.id_str;
		}
		$("#divTimeline").append("<hr/>");
		$("#spnPage").html(page + " page");
		//alert(max_id);
	}
</script>
<body>
	<h1>My Timeline</h1>
	<hr/>
	<a href="tweet_new.jsp">새 트윗 쓰기</a>
	<hr/><hr />
	<div id="divTimeline">
	</div>
	<div> 
		<input type="button" value="prev" onclick="go(-1)" />&nbsp;
		<span id="spnPage"></span>&nbsp;
		<input type="button" value="next" onclick="go(1)" />
	</div>
</body>
</html>