package Api.project;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmployeApi {

    @Test
    public void employee(){
        RestAssured.baseURI="https://reqres.in/";
        //////////////////////////////RequestObject/////////////////////////////////////////
        RequestSpecification httprequest = RestAssured.given();
        //////////////////////ResponseObject/////////////////////////////
        Response response = httprequest.request(Method.GET,"/api/users/2");
        ////////////////////GetStausCode///////////////////
        int statuscode = response.getStatusCode();
        System.out.println("the status code is "+statuscode);
        String thebody = response.getBody().asString();
        System.out.println("the body is "+thebody);
        String statusline=response.getStatusLine();
        System.out.println("the status line is "+statusline);
        String header = response.header("content-type");
        System.out.println(header);


    }














}
