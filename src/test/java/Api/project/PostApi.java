package Api.project;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostApi {
    @Test(dataProvider = "data")
    public void postapi(String name,String job){
        RestAssured.baseURI="https://reqres.in/";
        RequestSpecification httprequest = RestAssured.given();
        JSONObject bodyrequest = new JSONObject();
        bodyrequest.put("name",name);
        bodyrequest.put("job",job);
        ///////////////////////////////////Heade////////////////////////////////////////////
        httprequest.header("Content-type","application/json");
        httprequest.body(bodyrequest.toJSONString());
///////////////////////////////Response///////////////////////////////////////////////////////
        Response response = httprequest.request(Method.POST,"api/users");
        ///////////////////////////GetBody//////////////////////////////////////////////
        String body = response.getBody().asString();
        System.out.println("the body is "+body);
        //////////////////////////GetStatusCode//////////////////////////////////////////////////////
        int code = response.getStatusCode();
        System.out.println("the status code is "+code);
        System.out.println(response.getTime());
    }

    @DataProvider(name = "data")
    Object[][] getdata(){
        Object data[][]={{"walled","tester"},{"medo","developer"},{"maha","nurse"}};
        return data;
    }




}
