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

public class Post {

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
    void post(String name,String job){
        JSONObject js = new JSONObject();
        js.put("name",name);
        js.put("job",job);
        httprequest.header("Content-type","application/json");
        httprequest.body(js.toJSONString());
        response = httprequest.request(Method.POST,"api/users");
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods = {"post"})
    void checkstatuscode(){
        int statuscode = response.getStatusCode();
        System.out.println("the status code is "+statuscode);
        Assert.assertEquals(201,statuscode);
    }
    /////////////////////////////////////////////////////////////////////////////////
    @Test(dependsOnMethods = {"checkstatuscode"})
    void checkbody(){
        String name = response.jsonPath().getString("name");
        System.out.println("the name is "+name);
        String body = response.getBody().asString();
        System.out.println(body);
        Assert.assertEquals("mahaa",name);

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
        Assert.assertEquals("HTTP/1.1 201 Created",statuslinecode);

    }
    /////////////////////////////////DataBrovider////////////////////////////////////////
@DataProvider(name = "data")
Object[][] getdata()
{
    Object data[][] = {{"medo","tester"},{"walled","developer"},{"mahaa","nurse"}};
    return data;
}
    /////////////////////////////////////////////AfterClass///////////////////////////////////////////
    @AfterClass
    void after(){
        System.out.println("the test class is over");
    }
}
