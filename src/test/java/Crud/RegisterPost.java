package Crud;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPost {
    RequestSpecification httprequest;
    Response response;
    ///////////////////////////////////BeforeClass//////////////////////////////////////////////////////////////////
    @BeforeClass()
    public void befreclass(){
        RestAssured.baseURI="https://reqres.in/";
        httprequest=RestAssured.given();
    }
    ///////////////////////////////////////////////Test//////////////////////////////
    @Test(dataProvider = "data")
    void post(String email,String password){
        JSONObject js = new JSONObject();
        js.put("email",email);
        js.put("password",password);
        httprequest.header("Content-type","application/json");
        httprequest.body(js.toJSONString());
        response = httprequest.request(Method.POST,"api/register");
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods = {"post"})
    void checkstatuscode(){
        int statuscode = response.getStatusCode();
        System.out.println("the status code is "+statuscode);
        Assert.assertEquals(200,statuscode);
    }
    /////////////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods = {"checkstatuscode"})
    void checkbody(){
        String token = response.jsonPath().getString("token");
        System.out.println("the token is "+token);
        String body = response.getBody().asString();
        System.out.println(body);
        Assert.assertEquals("QpwL5tke4Pnpja7X4",token);

    }
    ///////////////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods = {"checkbody"})
    void contenttype(){
        String header=    response.header("content-type");
        System.out.println("the content type is "+header);

        Assert.assertEquals(header,"application/json; charset=utf-8");

    }
    //////////////////////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods = {"contenttype"})
    void checklincode(){
        String statuslinecode = response.getStatusLine();
        System.out.println(statuslinecode);
        Assert.assertEquals("HTTP/1.1 200 OK",statuslinecode);

    }
    /////////////////////////////////DataBrovider////////////////////////////////////////
    @DataProvider(name = "data")
    Object[][] getdata()
    {
        Object data[][] = {{"eve.holt@reqres.in","pistol"},{"eve.holt@reqres.in","pistol"},{"eve.holt@reqres.in","pistol"}};
        return data;
    }
    /////////////////////////////////////////////AfterClass///////////////////////////////////////////
    @AfterClass
    void after(){
        System.out.println("the test class is over");
    }
}
