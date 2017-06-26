<%@ page import="org.apache.commons.httpclient.*, 
				org.apache.commons.httpclient.methods.*,
				org.apache.commons.httpclient.*,
				org.apache.commons.httpclient.params.*,
				org.apache.commons.lang.*,
				java.net.*,
				java.io.*,java.util.*" %><%
 
 String queryString = request.getQueryString();

// String url = URLDecoder.decode(request.getParameter("url"), "utf-8");
 String url = request.getParameter("url");
 System.out.println(url);
 
 HttpClient client = new HttpClient();
 //client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
 //method.setQueryString(queryString);
 HttpMethod method = null;
 
 try {

     // Split this according to the type of request
     if (request.getMethod().equals("GET")) {
         method = new GetMethod(url);
     } else if (request.getMethod().equals("POST")) {
         method = new PostMethod(url);
         
         //브라우저로 부터 전달된 XML 또는 JSON을 그대로 httpClient의 요청 정보의 Body로 전달
         ((PostMethod)method).setRequestBody(request.getInputStream());  
         
     } else if (request.getMethod().equals("PUT")) {
         method = new PutMethod(url);
         
         //브라우저로 부터 전달된 XML 또는 JSON을 그대로 httpClient의 요청 정보의 Body로 전달
         ((PutMethod)method).setRequestBody(request.getInputStream());         
     } else if (request.getMethod().equals("DELETE")) {
         method = new DeleteMethod(url);

     } else {
         throw new NotImplementedException(
         	"This proxy only supports GET, PUT, DELETE and POST methods.");            	
     }
          
     method.setRequestHeader("ACCEPT", request.getHeader("ACCEPT"));
     method.setRequestHeader("CONTENT-TYPE", request.getHeader("CONTENT-TYPE"));
     
     // Execute the method
     client.executeMethod(method);
	 
     
     // Set the content type, as it comes from the server
     Header[] headers = method.getResponseHeaders();
     for (Header header : headers) {
         if ("Content-Type".equalsIgnoreCase(header.getName())) {
             response.setContentType(header.getValue());
         }
     }

	 String result = method.getResponseBodyAsString();
	 System.out.println(result);
	 response.setStatus(HttpStatus.SC_OK);
	 out.println(result); 
	 out.flush();
	 //out.close();
 } catch (HttpException e) {

     //log.error("Oops, something went wrong in the HTTP proxy", null, e);
     //writer.write(e.toString());
     e.printStackTrace();
     throw e;

 } catch (IOException e) {

     e.printStackTrace();
     //writer.write(e.toString());
     throw e;
 } finally {
	 
	 if (method != null) 
		 method.releaseConnection();
 }
 %>