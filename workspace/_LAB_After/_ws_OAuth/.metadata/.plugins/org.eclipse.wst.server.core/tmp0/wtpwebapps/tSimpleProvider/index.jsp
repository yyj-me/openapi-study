<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>OAuth Simple Provider Example!</h1>
	<hr>
	-----------------------------------<br/>
	Consumer 등록 단계 <br/>
	-----------------------------------<br/>	
	1. 사용자 ID로 로그인한다. 미리 제공되는 ID는 gdhong, user1, user2. 암호는 모두 12345!!<br/>
	2. Application 등록 페이지로 이동하여 Application Name과 Callback URL을 입력하고 등록버튼을 생성한다.<br/>
	3. Consumer Key & Consumer Secret 과 Access Token & Access Token Secret을 생성하여 tbl_users
	테이블에 등록한다. <br/><br/>
	-----------------------------------<br/>
	requestToken 요청 단계  - request_token 서블릿<br/>
	-----------------------------------<br/>
	1. Consumer가 request Token을 요청해오면 요청 정보를 파싱한다.<br/>
	2. 요청 정보 내용<br/>
	  - oauth_consumer_key : <br/>
	  - oauth_signature_method :<br/>
	  - oauth_timestamp :<br/>
	  - oauth_nonce :<br/>
	  - oauth_callback : <br/>
	  - oauth_signature :<br/><br/>
	  
	3. 확인할 내용<br/>
	  - consumer_key에 해당하는 레코드를 tbl_oauth_key 테이블에서 읽어냄. --> 레코드가 없으면 오류!!<br/>
	  - consumersecret값을 읽어냄 -> 이값으로 consumerkey~callback까지를 서명하여 생성한 값이 
	          전송된 oauth_signature값과 일치하느지 여부 확인. --> 일치하지 않으면 오류!! <br/>
	  - oauth_timestamp 값의 시간값과 서버에서 생성한 timestamp값의 차이가 10분 이상(이것은 
	         서비스 프로바이더가 기준을 세워야 함.)이라면 유효한 request가 아니라고 판단하여 오류발생!!<br/>
	  - callbackUrl: 값이 전송되지 않았다면, callbackUrl이 인증 후 이동하는 경로가 됨.<br/>
	  - 모두 정상적이라면 requesttoken과 request token secret 생성하고, 
	     tbl_request_token 테이블에 레코드 저장 
	     --> 저장데이터는  requesttoken,request token secret, consumer key<br/> 
	4. 확인이 완료되었다면, 다음 정보를 Consumer에게 전송<br/>
      - oauth_token : request_token<br/>
      - oauth_token_secret : request_token_secret<br/>
      - oauth_callback_confirmed : true<br/>
      - 이외의 추가적인 파라미터<br/><br/>
 	-----------------------------------<br/>
	Authorize 요청 단계- authorize 서블릿<br/>
	-----------------------------------<br/>     
    1. 요청 정보를 확인하여 oauth_token 값을 파싱한다.<br/>
    2. 로그인 페이지로 이동한다. --> 로그인하고 승인하도록 한다. 만일 로그인된 상태라면,
                  승인버튼을 클릭하여 승인하도록 한다.<br/>
    3. 승인버튼을 클릭하면, tbl_request_token 테이블에서 request_token(oauth_token) 값에 대한 레코드를 
                찾아 verifier 값을 생성한 다음 레코드 값을 변경한다. (비어있던 verifier 필드값 생성)<br/>
    4. 그런 후 미리 등록하거나 요청받은 callback URL로 이동시킨다.
                이동시킬 때 oauth_token과 verifier 값을 queryString 형태로 붙여 전송한다.<br/><br/>
	      
 	-----------------------------------<br/>
	AccessToken 요청 단계 <br/> - access_token 서블릿<br/>
	-----------------------------------<br/>     
	1. 요청 정보를 확인하여 각 정보를 파싱한다.
	    - oauth_consumer_key :
		- oauth_signature_method :
		- oauth_token :
		- oauth_verifier : 
		- oauth_callback : 
		- oauth_timestamp : 
		- oauth_nonce : 
		- oauth_signature : 
	2. 1에서 oauth_signature값은 나머지 값을 HMAC + Consumer Secret 으로 서명한 결과값 의미한다.
	3. Consumer Key 정보를 이용하여 tbl_auth_key 테이블에서 Consumer Key에 대한 레코드 정보를 로딩한다.
	4. 요청 정보에서, oauth_signature값과, 나머지 값들을 테이블에서 읽어낸 Consumer Secret 값으로 
	    서명한 값을 비교하여 동일한지를 확인한다. 다르다면 오류 페이지로!!
    5. oauth_timestamp 값의 시간 값과 서버에서 생성한 timestamp값의 차이가 10분 이상(이것은 
	         서비스 프로바이더가 기준을 세워야 함.)이라면 유효한 request가 아니라고 판단하여 오류발생!!<br/>
	6. 앞의 모든 과정이 순조롭다면, tbl_request_token 테이블의 레코드중 oauth_token에 해당하는 
	     레코드를 삭제하고
	7. Consumer 테이블의 Consumer Key에 해당하는 Access Token 정보 쌍(oauthtoken, 
	   oauthtokensecret 필드값)을 읽어내어 Consumer에게 응답한다.
	
	-------------------------------------<br/>
	Access Token 과 Access TOken Secret을 받은 Consumer의 처리<br/>
    : Consumer는 Request Token & Secret 대신 Access Token & Secret을 넘겨받았다.<br/>
       이정보를 이용해 서명하여 Provider에게 보호된 자원을 요청한다.<br/>
	-------------------------------------<br/>
	1. 다음 정보를 생성한 정보와 함께 Protected Resource에 접근한다.<br/>
	- oauth_consumer_key :<br/>
	- oauth_token : Access Token<br/>
	- oauth_signature_method :<br/>
    - oauth_timestamp : <br/>
	- oauth_nonce :<br/>
	- oauth_version :<br/>
	- oauth_signature : Access Token Secret으로 앞의 값들을 서명한 값!! <br/>
	
	
	
	
		
</body>
</html>