package restAssuredTestClasses;



//import com.fasterxml.jackson.databind.util.JSONPObject;


import io.restassured.RestAssured;
import io.restassured.internal.util.IOUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restAssuredClasses.PostClass;
import restAssuredClasses.RecievedData;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

import org.apache.commons.io.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class RestAssuredRequestClass {
	@Test(priority=1)
    public void GetData(){

		
		
       RestAssured.baseURI="http://localhost:3000/posts";
       given().contentType("application/json")
       .when().get("http://localhost:3000/posts")
       .then().log().all();


    }
	//How to call Post Method using Json Object in RestAssured API
	
	@Test(priority=2)
	    public void PostDataByJsonObject(){

		JSONObject json=new JSONObject();
          int randomid=new Random().nextInt(1000);
          json.put("id",randomid);
          json.put("title","title"+randomid);
          json.put("author","authortest"+randomid);
          RestAssured.baseURI="http://localhost:3000";
          
	     
	      given().contentType("application/json")
	      .and()
	      .body(json.toString())
	      .when()
	      .post("/posts")
	      .then()
	      .statusCode(201)
	      .log().all()
	      
	      ;
	      
	      GetData();

	    }
	//How to call Post Method using Json file in RestAssured API
	@Test(priority=3)
    public void PostDataByJsonFile() throws IOException{

	FileInputStream fileinputstream=new FileInputStream(new File(".\\jsonfiles\\posts.json"));
	String Jsonbody=org.apache.commons.io.IOUtils.toString(fileinputstream,"utf-8");
      RestAssured.baseURI="http://localhost:3000";
      System.out.print(Jsonbody);
     
      given().contentType("application/json")
      .and()
      .body(Jsonbody)
      .when()
      .post("/posts")
      .then()
    
      .log().all()
      
      ;
      
     

    }
	
	//How to call Post Method and Receive response in Java Class Object in RestAssured API
		@Test(priority=4)
	    public void PostDataByJavaClass() throws IOException{

			 int randomid=new Random().nextInt(1000);
		PostClass jsondata=new PostClass(randomid,"title"+randomid,"author"+randomid);
		RecievedData Res=new RecievedData();
		Gson G=new GsonBuilder().create();
		JSONObject json=new JSONObject();
        
        json.put("id",jsondata.getId());
        json.put("title",jsondata.getTitle());
        json.put("author",jsondata.getAuthor());
        RestAssured.baseURI="http://localhost:3000";
        
	     Response response=
	      given().contentType("application/json")
	      .and()
	      .body(json.toString())
	      .when()
	      .post("/posts")
	      .then()
	      .statusCode(201)
	      .log().all()
	      .and()
	      .extract().response();
	      Res=G.fromJson(response.prettyPrint(), RecievedData.class);
	      
	     Assert.assertEquals(Res.getId(), jsondata.getId());
	     Assert.assertEquals(Res.getTitle(), jsondata.getTitle());
	     Assert.assertEquals(Res.getAuthor(), jsondata.getAuthor());

	    }
		//How to call Post Method by string in RestAssured API
		@Test (priority=5)
		 public void PostDataByString() throws IOException{
			 
			
		        RestAssured.baseURI="http://localhost:3000";
		        
			     Response response=
			      given().contentType("application/json")
			      .and()
			      .body("{"+
			    	  "\"id\": 6060,"+
			    	 "\" title\": \"UpdatedTitle6060\","+
			    	 "\"author\": \"UpdatedAuthor6060\" "+
			    	  "}")
			      .when()
			      .post("/posts")
			      .then()
			      .statusCode(201)
			      .log().all()
			      .and()
			      .extract().response();
			     JsonPath jsonxpath=new JsonPath(response.asString());
			     System.out.println("ID  IS "+" "+jsonxpath.get("id"));
			 
		 }
		 @Test(priority=6)
		public void DeletePostsData() throws IOException{
			 
				
		        RestAssured.baseURI="http://localhost:3000";
		        
			     
			      given().contentType("application/json")
			       .when()
			      .delete("posts/"+400)
			      .then()
			      .statusCode(200)
			      .log().all();
			      
			 
		 }
		 
		 @Test(priority=7)
			public void EditPostsData() throws IOException{
				 
					
			 RestAssured.baseURI="http://localhost:3000";
		        
		     Response response=
		      given().contentType("application/json")
		      .and()
		      .body("{"+
		    	  "\"id\": 1,"+
		    	 "\" title\": \"UpdatedTitleUpdated\","+
		    	 "\"author\": \"UpdatedAuthorUpdated\" "+
		    	  "}")
		      .when()
		      .put("/posts/1")
		      .then()
		      .statusCode(200)
		      .log().all()
		      .and()
		      .extract().response();
		     JsonPath jsonxpath=new JsonPath(response.asString());
		     System.out.println("ID  IS "+" "+jsonxpath.get("id"));
				      
				 
			 }
}


