package Crud;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Put {
    RequestSpecification httprequest;
    Response response;
    @BeforeClass
    public void befreclass(){
        RestAssured.baseURI="https://reqres.in/";
        httprequest=RestAssured.given();
        response = httprequest.request(Method.GET,"api/users?page=2");
        int id = response.jsonPath().getInt("data[0].id");
        JSONObject js = new JSONObject();
        js.put("email","walled@gmail.com");
        js.put("first_name","walled");
        js.put("last_name","medo");
        js.put("avatar","https://reqres.in/img/faces/7-image.jpg");

        httprequest.header("Content-type","application/json");
        httprequest.body(js.toJSONString());
        response = httprequest.request(Method.PUT,"api/users/"+id+"");

    }
    @Test(priority = 1)
    void checkstatuscode(){
        int statuscode = response.getStatusCode();
        System.out.println("the status code is "+statuscode);
        Assert.assertEquals(200,statuscode);
    }
    @Test(priority = 2)
    void checkbody(){
        String body = response.getBody().asString();
        String lastname = response.jsonPath().getString("last_name");

        System.out.println("the body is "+body);
        Assert.assertEquals(body.contains("walled"),true);
        System.out.println("the last name is  "+lastname);
        Assert.assertEquals(lastname,"medo");
    }

    @Test(priority = 3)
    void contenttype(){
        String header=    response.header("content-type");
        System.out.println("the content type is "+header);

        Assert.assertEquals(header,"application/json; charset=utf-8");

    }

    @AfterClass
    void after(){
        System.out.println("the test class is over");
    }
}
