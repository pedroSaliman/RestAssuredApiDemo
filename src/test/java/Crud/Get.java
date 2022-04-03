package Crud;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Get {

    RequestSpecification httprequest;
    Response response;
    @BeforeClass
    public void befreclass(){
        RestAssured.baseURI="https://reqres.in/";
        httprequest=RestAssured.given();
        response = httprequest.request(Method.GET,"api/users?page=2");
    }
    @Test(priority = 1)
    void checkstatuscode(){
        int statuscode = response.getStatusCode();
        System.out.println("the status code is "+statuscode);
        Assert.assertEquals(200,statuscode);
    }
@Test(priority = 2)
    void checkbody(){
       String email = response.jsonPath().getString("data[0].email");
    System.out.println("the email is "+email);
    String body = response.getBody().asString();
    System.out.println(body);
       Assert.assertEquals("michael.lawson@reqres.in",email);
}
@Test(priority = 3)
    void contenttype(){
   String header=    response.header("content-type");
    System.out.println("the content type is "+header);

    Assert.assertEquals(header,"application/json; charset=utf-8");

}
@Test(priority = 4)
    void checklincode(){
    String statuslinecode = response.getStatusLine();
    System.out.println(statuslinecode);
    Assert.assertEquals("HTTP/1.1 200 OK",statuslinecode);

}

@AfterClass
    void after(){
    System.out.println("the test class is over");
}
}
