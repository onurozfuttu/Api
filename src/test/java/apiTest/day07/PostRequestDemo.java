package apiTest.day07;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser() {


        String jsonBody = "{\n" +
                "  \"name\": \"John\",\n" +
                "  \"email\": \"bigJ@kraft.com\",\n" +
                "  \"password\": \"tests1234\",\n" +
                "  \"about\": \"About Me\",\n" +
                "  \"terms\": \"10\"\n" +
                "}";

        Response response = RestAssured.given()
                .accept(ContentType.JSON) //send me body as json format
                .and()
                .contentType(ContentType.JSON) // i am sending json body
                .and()
                .body(jsonBody)
                .when()
                .post("/allusers/register");


        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser2() {

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "Alucard");
        requestMap.put("email", "alucard@kraft.com");
        requestMap.put("password", "test123");
        requestMap.put("about", "about me");
        requestMap.put("terms", "5");

        Response response = RestAssured.given()
                .accept(ContentType.JSON) //send me body as json format
                .and()
                .contentType(ContentType.JSON) // i am sending json body
                .and()
                .body(requestMap)
                .when()
                .post("/allusers/register");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.body().asString().contains("token"));

        response.prettyPrint();
    }

    @Test
    public void postNewUser3() {

        NewUserInfo newUserInfo = new NewUserInfo();
        newUserInfo.setName("Joshua1");
        newUserInfo.setEmail("joshjosh22@kraft.com");
        newUserInfo.setPassword("test123");
        newUserInfo.setAbout("about");
        newUserInfo.setTerms("5");

        Response response = RestAssured.given()
                .accept(ContentType.JSON) //send me body as json format
                .and()
                .contentType(ContentType.JSON) // i am sending json body
                .and()
                .body(newUserInfo) //Serialization
                .when()
                .post("/allusers/register");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser4() {
        NewUserInfo newUserInfo = new NewUserInfo("Seba","seba1@kraft.com","test123","about","5");

        Response response = RestAssured.given()
                .accept(ContentType.JSON) //send me body as json format
                .and()
                .contentType(ContentType.JSON) // i am sending json body
                .and()
                .body(newUserInfo) //Serialization
                .when()
                .post("/allusers/register");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.body().asString().contains("token"));

    }
}
