package httpclient_test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class Test01 {

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("utf-8");
		
		String url = "http://70.12.107.190:8000/contactsvc/contacts";
		GetMethod method = new GetMethod(url);
//		method.getParams().setParameter("pageno", 2);
//		method.getParams().setParameter("pagesize", 5);
		
		int status = client.executeMethod(method);
		if(status == 200) {
			String json = method.getResponseBodyAsString();
			System.out.println(json);
		} else {
			System.out.println("Status : " + status);
		}
	}
}