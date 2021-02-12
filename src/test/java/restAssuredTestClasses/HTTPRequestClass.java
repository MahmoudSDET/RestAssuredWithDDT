package restAssuredTestClasses;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import groovy.xml.Entity;
import io.restassured.path.json.JsonPath;

public class HTTPRequestClass {

	
	@Test
	public void getMethod() throws ClientProtocolException, IOException
	{
		//create client
		CloseableHttpClient client=HttpClients.createDefault() ;
		
		//create request
		HttpGet request =new HttpGet("http://localhost:3000/posts/3");
		
		//adding header
		request.addHeader("Content-Type","application/json");
		
		//execute command
		CloseableHttpResponse response=client.execute(request);
		
		//Asserting The response
		int code=response.getStatusLine().getStatusCode();
		System.out.println("the response code is :" +code);
		
		assertEquals(code, 200);
		
		String responsestring=EntityUtils.toString(response.getEntity(),"UTF-8");
		System.out.println(responsestring);
		JsonPath jsxpath=new JsonPath(responsestring);
		int id=jsxpath.getInt("id");
		System.out.println(id);
		assertEquals(id, 3);
		
		
	}
	@Test
	public void PostMethod() throws ClientProtocolException, IOException
	{
		//create client
		CloseableHttpClient client=HttpClients.createDefault() ;
		String bodyPayload="{"+
		    	  "\"id\": 30,"+
		    	 "\" title\": \"UpdatedTitle30\","+
		    	 "\"author\": \"UpdatedAuthor30\" "+
		    	  "}";
		
		//create request
		HttpPost request =new HttpPost("http://localhost:3000/posts");
		
		//adding header
		request.addHeader("Content-Type","application/json");
		request.setEntity(new StringEntity(bodyPayload));
		//execute command
		CloseableHttpResponse response=client.execute(request);
		
		//Asserting The response
		int code=response.getStatusLine().getStatusCode();
		System.out.println("the response code is :" +code);
		
		assertEquals(code, 201);
		
		String responsestring=EntityUtils.toString(response.getEntity(),"UTF-8");
		System.out.println(responsestring);
		JsonPath jsxpath=new JsonPath(responsestring);
		int id=jsxpath.getInt("id");
		System.out.println(id);
		assertEquals(id, 30);
		
		
	}
}



