package apiTest.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class C02_PetStoreWithPathParam {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://petstore.swagger.io/v2";
    }

    @Test
    public void petStoreFindPetByID1() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("pet/566");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void petStoreFindPetByID2() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("petID",566)
                .when()
                .get("pet/{petID}");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void petStoreFindPetByID3() {
        int petID = 566;
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",petID)
                .when()
                .get("pet/{id}");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
    }
}
