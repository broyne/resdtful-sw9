package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class BookingAssertion {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given()
                .when()
                .get("/booking/10")
                .then().statusCode(200);
    }

    @Test
    public void firstNameTest() {
        response.body("", hasKey("firstname"));
    }

    @Test
    public void lastNameTest() {
        response.body("", hasKey("lastname"));
    }

    @Test
    public void totalPriceTest() {
        response.body("", hasKey("totalprice"));
    }

    @Test
    public void depositePaidTest() {
        response.body("", hasKey("depositpaid"));
    }

    @Test
    public void checkInTest() {
        response.body("bookingdates", hasKey("checkin"));
    }

    @Test
    public void checkOutTest() {
        response.body("bookingdates", hasKey("checkout"));
    }

}
