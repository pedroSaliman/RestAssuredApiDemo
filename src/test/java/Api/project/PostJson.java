package Api.project;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

public class PostJson {
    @Test
    public void jsonapi(){
        RestAssured.baseURI="http://localhost:3000";
        RequestSpecification httprequest = RestAssured.given();
        JSONObject js = new JSONObject();
        js.put("title","ruby");
        js.put("author","ledo");
        httprequest.header("Content-type","application/json");
        httprequest.body(js.toJSONString());

        Response response = httprequest.request(Method.POST,"/posts");
        int code = response.getStatusCode();
        System.out.println("the status code is " +code);
String bo = response.body().asPrettyString();
        System.out.println(bo);
Assert.assertEquals(bo.contains("ruby"),true);
        String header= response.header("content-type");
        System.out.println(header);

    }
}
