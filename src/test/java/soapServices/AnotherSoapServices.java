package soapServices;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class AnotherSoapServices {
	
	
	private static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
	
	@Test(priority=2)
    public void PostDataByXMLFile() throws IOException{

	//FileInputStream fileinputstream=new FileInputStream(new File(".\\xmlfiles\\SoapXMLRequestWithParameters.xml"));
	String req= generateStringFromResource (".\\xmlfiles\\SoapXMLRequestWithParameters.xml")
			.replace("Currencyfrom", "USD").
			replace("Currencyto", "INR");
      RestAssured.baseURI="http://currencyconverter.kowabunga.net/converter.asmx";
     // System.out.print(Jsonbody);
     
      Response response=
      given().contentType("text/xml").and()
      
      .when()
      .body(req)
      .post()
      .then()
      
      
    
      .log().all().extract().response()
      
      ;
      XmlPath xmlxpath=new  XmlPath(response.asString());
		String rates=xmlxpath.getString("GetConversionRateResult");
		System.out.println(rates);
     

    }

}
