package apiTest.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
/**
 * Headers test
 * Given accept type is JSON
 * And pathParam id is 111
 * When the user sends a GET request to "/allusers/getbyid/{id}"
 * And content-type is "application/json; charset=UTF-8"
 *
 */

public class C04_UserGetRequestAndVerifyHeaders {
    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void headerTest() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");

        //response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //verify the header
        Assert.assertEquals(response.header("Content-Length"),"606");
        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=UTF-8");
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
    }
}
