package soapServices;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;

public class TestSoapServicesByHttpRequest {

	
	@Test
	public void PostMethod() throws ClientProtocolException, IOException
	{
		File soapRequestFiles=new File(".\\xmlfiles\\request1.xml");
		//create client
		CloseableHttpClient client=HttpClients.createDefault() ;
		
		
		//create request
		HttpPost request =new HttpPost("http://currencyconverter.kowabunga.net/converter.asmx");
		
		//adding header
		request.addHeader("Content-Type","text/xml");
		request.setEntity(new InputStreamEntity(new FileInputStream(soapRequestFiles)));
		//execute command
		CloseableHttpResponse response=client.execute(request);
		
		//Asserting The response
		int code=response.getStatusLine().getStatusCode();
		System.out.println("the response code is :" +code);
		
		assertEquals(code, 200);
		
		String responsestring=EntityUtils.toString(response.getEntity(),"UTF-8");
		//System.out.println(responsestring);
		XmlPath xmlxpath=new  XmlPath(responsestring);
		String rates=xmlxpath.getString("GetConversionRateResult");
		System.out.println(rates);
		
		
		
	}
}
