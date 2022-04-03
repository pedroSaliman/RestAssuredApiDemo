package Crud;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Delete {

    RequestSpecification httprequest;
    Response response;
    @BeforeClass
    public void befreclass(){
        RestAssured.baseURI="https://reqres.in/";
        httprequest=RestAssured.given();
        response = httprequest.request(Method.GET,"api/users?page=2");
        int id = response.jsonPath().getInt("data[0].id");
        response = httprequest.request(Method.DELETE,"api/users/"+id+"");

    }
    @Test(priority = 1)
    void checkstatuscode(){
        int statuscode = response.getStatusCode();
        System.out.println("the status code is "+statuscode);
        Assert.assertEquals(204,statuscode);
    }

    @Test(priority = 2)
    void contenttype(){
        String header=    response.header("content-type");
        System.out.println("the content type is "+header);

        Assert.assertEquals(header,null);

    }

    @AfterClass
    void after(){
        System.out.println("the test class is over");
    }




}
