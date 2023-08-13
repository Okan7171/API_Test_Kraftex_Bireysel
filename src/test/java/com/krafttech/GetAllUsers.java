package com.krafttech;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllUsers {
    String BaseUrl= "https://www.krafttechexlab.com/sw/api/v1/";
    @Test
    public void simpleGetRequest(){
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(BaseUrl + "allusers/alluser?pagesize=5&page=1");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

    }
    @Test
    public void simpleGetRequest_2(){
        Response response = RestAssured
                .given()
                .queryParam("pagesize", 5)
                .queryParam("page", 1)
                .when()
                .get(BaseUrl + "allusers/alluser");
        System.out.println("response.statusCode() = " + response.statusCode());
       // response.prettyPrint();
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"name[0]\") = " + response.path("name[0]"));

    }
    @Test
    public void simpleGetRequest_3_HamCrestMatchers(){
        ValidatableResponse validatableResponse = RestAssured
                .given()
                .when()
                .get(BaseUrl + "allusers/alluser?pagesize=5&page=1")
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void simpleGetRequest_4_AsString(){
        Response response = RestAssured
                .given()
                .queryParam("pagesize", 5)
                .queryParam("page", 1)
                .when()
                .get(BaseUrl + "allusers/alluser");
        System.out.println("response.body().asString() = " + response.body().asString());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.body().asString().contains("MercanS"));
    }
    @Test
    public void simpleGetRequest_5_Path(){
        Response response = RestAssured
                .given()
                .queryParam("pagesize", 5)
                .queryParam("page", 1)
                .when()
                .get(BaseUrl + "allusers/alluser");
        //response.prettyPrint();
        int id = response.path("id[1]");
        Assert.assertEquals(id, 5);

        Assert.assertEquals(response.path("name[1]"), "isa akyuz");
        Assert.assertEquals(response.path("name[-1]"), "Sebastian");
        Assert.assertEquals(response.path("skills[0][0]"), "PHP");

    }

}
