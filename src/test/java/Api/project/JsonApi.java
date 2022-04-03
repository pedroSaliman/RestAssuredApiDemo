package Api.project;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;

public class JsonApi {
    @Test
    public void jsonapi(){
        RestAssured.baseURI="http://localhost:3000";
        RequestSpecification httprequest = RestAssured.given();
        Response response = httprequest.request(Method.GET,"/posts");
        int code = response.getStatusCode();
        System.out.println("the status code is " +code);
        String body = response.getBody().asString();
        System.out.println("the body is "+ body);
       String title= response.jsonPath().get("title[0]");
       Assert.assertEquals("json-server",title);
        System.out.println(title);
       String header= response.header("content-type");
        System.out.println(header);
    }
}
