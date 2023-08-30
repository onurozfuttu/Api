package apiTest.day07;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class PostEducation {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser4_withEducation() {
        NewUserInfo newUserInfo = new NewUserInfo("Johna","joo133@kraft.com","test123","about","5");

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
        response.prettyPrint();
        String user_token = response.path("token");


        System.out.println("----");

        String educationBody = "{\n" +
                "  \"school\": \"Ankara University\",\n" +
                "  \"degree\": \"Associate Degree\",\n" +
                "  \"study\": \"Computer Programming\",\n" +
                "  \"fromdate\": \"2020-01-01\",\n" +
                "  \"todate\": \"2022-08-07\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Description\"\n" +
                "}";

        response = RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .header("token",user_token)
                .when()
                .post("/education/add");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test
    public void postNewUserAndVerify() {
        String name = "Alucard";
        String email = "alucardo112@kraft.com";
        String password = "test123";
        String about = "about me";
        String terms = "4";

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", name);
        requestMap.put("email", email);
        requestMap.put("password", password);
        requestMap.put("about", about);
        requestMap.put("terms", terms);

        Response response = RestAssured.given()
                .accept(ContentType.JSON) //send me body as json format
                .and()
                .contentType(ContentType.JSON) // i am sending json body
                .and()
                .body(requestMap)
                .when()
                .post("/allusers/register");

        Assert.assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        String user_token = response.path("token");
        int userID = response.path("id");

        Map<String, Object> educationBody = new HashMap<>();
        educationBody.put("school", "ODTU");
        educationBody.put("degree", "Bachelor's Degree");
        educationBody.put("study", "Computer Engineering");
        educationBody.put("fromdate", "2019-02-03");
        educationBody.put("todate", "2023-02-05");
        educationBody.put("current", "false");
        educationBody.put("description", "description");

        response = RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .header("token",user_token)
                .when()
                .post("/education/add");

        String userSchool = response.path("school");

        //verify body
        int edu_id = response.path("id");

        response = RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .header("token",user_token)
                .pathParam("id",edu_id)
                .when()
                .get("/education/getbyid/{id}");

        response.prettyPrint();

        //verify with path
        Assert.assertEquals((int) response.path("userid"),userID);
        Assert.assertEquals(response.path("school"), userSchool);

        //verify using hamcrest matcher
        RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .header("token",user_token)
                .pathParam("id",edu_id)
                .when()
                .get("/education/getbyid/{id}")
                .then()
                .assertThat()
                .body("school", Matchers.equalTo(userSchool),
                        "userid", Matchers.equalTo(userID),
                        "study",Matchers.equalTo("Computer Engineering"))
                .log().all();

    }
}
