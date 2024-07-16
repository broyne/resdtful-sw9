package com.restful.booker.crudtest;

import com.restful.booker.bookinginfo.BookingSteps;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtil;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingCRUDTest extends TestBase {

    static String fName = TestUtil.generateFirstName();
    static String lName = TestUtil.generateLastName();
    static int totalPrice = 1000;
    static boolean depositPaid = true;
    static String additionalNeeds = "Breakfast";

    static int id;

    @Steps
    BookingSteps bookingSteps;

    @Title("This will create new booking")
    @Test
    public void a_verifyBookingCreatedSuccessfully() {
        String checkIn = "2024-07-03";
        String checkOut = "2024-07-10";

        ValidatableResponse response = bookingSteps.createBooking(fName, lName, totalPrice, depositPaid, bookingSteps.setDate(checkIn, checkOut), additionalNeeds).statusCode(200);
        id = response.log().all().extract().path("bookingid");
    }

    @Title("Verify that booking is added successfully to the list")
    @Test
    public void b_verifyBookingGetByIdSuccessfully() {
        bookingSteps.getBookingIDbByInfo(id).statusCode(200);
    }

    @Title("Update the booking info and verify the updated information")
    @Test
    public void c_verifyBookingUpdateSuccessfully() {
        fName = fName + "updated";
        lName = lName + "updated";
        String checkIn = "2024-07-05";
        String checkOut = "2024-07-12";

        bookingSteps.updateBookingSuccessfully(id, fName, lName, totalPrice, depositPaid, bookingSteps.setDate(checkIn, checkOut), additionalNeeds).statusCode(200);

    }

    @Title("Delete the booking and verify its deleted")
    @Test
    public void d_verifyBookingDeletedSuccessfully() {
        bookingSteps.deleteBooking(id);
    }
}
