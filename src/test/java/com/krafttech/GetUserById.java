package com.krafttech;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserById {
    String BaseUrl= "https://www.krafttechexlab.com/sw/api/v1/";
    @Test
    public void TestWithPathMethod(){
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .pathParam("id", 24)
                .when().get(BaseUrl + "allusers/getbyid/{id}");
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());
        System.out.println("response.path(\"name\") = " + response.path("name"));
        Assert.assertEquals(response.path("name").toString(), "[mike]");
       // response.prettyPrint();
        Assert.assertEquals(response.path("name[0]"), "mike");
        //Assert.assertEquals(response.path("id[0]"), "24");
        int id = response.path("id[0]");
        Assert.assertEquals(id, 24);
    }
    @Test
    public void TestWithPathMethod_2(){
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .pathParam("id", 24)
                .when().get(BaseUrl + "allusers/getbyid/{id}");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=UTF-8");



 }
}