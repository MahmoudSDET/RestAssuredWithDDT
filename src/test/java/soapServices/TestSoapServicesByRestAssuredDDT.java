package soapServices;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DataUtility.ExcelReaderAndWriter;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class TestSoapServicesByRestAssuredDDT {
	
	
	
	
	@Test(priority=1)
    public void PostDataByXMLBodyFile() throws IOException{
     /*
	FileInputStream fileinputstream=new FileInputStream(new File(".\\xmlfiles\\request1.xml"));
	String Jsonbody=org.apache.commons.io.IOUtils.toString(fileinputstream,"utf-8");
	
	*/
		String req= generateStringFromResource (".\\xmlfiles\\request1.xml")
				.replace("param_1","5").
				replace("param_2","2");
      RestAssured.baseURI="https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService";
     // System.out.print(Jsonbody);
     
      Response response=
      given().contentType("text/xml")
      .and()
      .body(req)
      .when()
      .post("/Calc.asmx")
      .then()
    
      .log().all().extract().response()
      
      ;
      XmlPath xmlxpath=new  XmlPath(response.asString());
		String rates=xmlxpath.getString("AddResult");
		System.out.println(rates);
     

    }
	
	
	private static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
	
	
	ExcelReaderAndWriter ex=new ExcelReaderAndWriter();
	
	 @DataProvider(name = "data")
	 public Object[][] ReaddataSoupServices() throws IOException{
		 
		 return ex.dataSoupServices();
		 
		 
	 }
	
	
	
	@Test(priority=2,dataProvider = "data")
    public void PostDataByXMLFile(Map<Object, Object> map) throws IOException{
      
	//FileInputStream fileinputstream=new FileInputStream(new File(".\\xmlfiles\\SoapXMLRequestWithParameters.xml"));
	String req= generateStringFromResource (".\\xmlfiles\\SoapXMLRequestWithParameters.xml")
			.replace("CF", map.get("Currency_Converted_From").toString()).
			replace("CT",map.get("Currency _Converted _To").toString());
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
	
	
	@Test(priority=2)
    public void GetCountriesDataByXMLFile() throws IOException{

	//FileInputStream fileinputstream=new FileInputStream(new File(".\\xmlfiles\\SoapXMLRequestWithParameters.xml"));
	String req= generateStringFromResource (".\\xmlfiles\\LastRequestTime.xml");
			
      RestAssured.baseURI="http://currencyconverter.kowabunga.net/converter.asmx";
     // System.out.print(Jsonbody);
     
      Response response=
      given().contentType("text/xml").and()
      
      .when()
      .body(req)
      .post()
      .then()
      
      
    
      .log().all().extract().response();
      
      
     
     

    }
	
	
	
	
}
