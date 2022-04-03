package Api.project;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;

public class FirstApi {
    @Test
    public void rest1(){
        RestAssured.baseURI="https://reqres.in/";
        RequestSpecification httprequest=RestAssured.given();
        Response response = httprequest.request(Method.GET,"api/users?page=2");
        /////////////////////////////ResponseBody/////////////////////////////////
     String responseBody =    response.getBody().asString();
        System.out.println("response body is "+ responseBody);
        ////////////////////////////////StatusCode///////////////////////
        int statuscode = response.getStatusCode();
        System.out.println("the status code is ! "+statuscode);
        ///////////////////////Assertion For Code////////////////////////////
        Assert.assertEquals(statuscode,200);
        //////////////////////////StatusLine///////////////////////
      String statusline=  response.getStatusLine();
        System.out.println(statusline);
        System.out.println(response.jsonPath().get("data[0].email"));
        /////////////////////Capture Headers///////////////////////////////////////
        String header = response.header("content-type");
        System.out.println(header);



    }
}
