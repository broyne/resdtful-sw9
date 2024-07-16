package com.restful.booker.bookinginfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class BookingSteps {
    public HashMap<String, String> setDate(String checkIn, String checkOut) {
        HashMap<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkIn);
        bookingDates.put("checkout", checkOut);
        return bookingDates;
    }

    @Step("Creating booking with fName : {0},lName : {1}, totalPrice : {2},depositPaid: {3}, bookingDates : {4}, and additionalNeeds : {5}")
    public ValidatableResponse createBooking(String fName, String lName, int totalPrice, boolean depositPaid, HashMap<String, String> bookingDates, String additionalNeeds) {

        BookingPojo bookingPojo = BookingPojo.getBookingPojo(fName, lName, totalPrice, depositPaid, bookingDates, additionalNeeds);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .post(EndPoints.GET_BOOKING_INFO)
                .then().log().all();

    }
    @Step("Getting booking info by id : {0}")
    public ValidatableResponse getBookingIDbByInfo(int id) {

        return SerenityRest.given()
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_BOOKING_INFO_BY_ID)
                .then();
    }

    @Step("Updating booking with id : {0}, fName : {1},lName : {2}, totalPrice : {3},depositPaid: {4}, bookingDates : {5}, and additionalNeeds : {6}")
    public ValidatableResponse updateBookingSuccessfully(int id, String fName, String lName, int totalPrice, boolean depositPaid, HashMap<String, String> bookingDates, String additionalNeeds) {

        BookingPojo bookingPojo = BookingPojo.getBookingPojo(fName, lName, totalPrice, depositPaid, bookingDates, additionalNeeds);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Connection", "keep-alive")
                .pathParam("id", id)
                .body(bookingPojo)
                .when()
                .put(EndPoints.GET_BOOKING_INFO_BY_ID)
                .then();

    }
    @Step("Delete booking by id : {0}")
    public ValidatableResponse deleteBooking(int id) {
        return SerenityRest.given()
                .pathParam("id", id)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete(EndPoints.GET_BOOKING_INFO_BY_ID)
                .then();

    }

}

