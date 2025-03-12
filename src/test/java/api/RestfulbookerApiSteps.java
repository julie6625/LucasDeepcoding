package api;

import dto.TokenDto;
import dto.BookingDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.ValidationUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class RestfulbookerApiSteps {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private TokenDto tokenDto;
    private BookingDto bookingDto;
    private Response response;
    private Response originalResponse;

    public void createtoken(String username, String password) {
        JSONObject authpayload = new JSONObject();
        authpayload.put("username",username);
        authpayload.put("password",password);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(authpayload.toString())
                .post(BASE_URL + "/auth");

        tokenDto = new TokenDto(response.jsonPath().getString("token"));
        System.out.println(response.getBody().asPrettyString());
    }

    public void createBooking(String firstname, String lastname, int totalprice, boolean deposit, String checkin, String checkout, String additionalneeds) {
        JSONObject BookingPayload = new JSONObject();
        BookingPayload.put("firstname", firstname);
        BookingPayload.put("lastname", lastname);
        BookingPayload.put("totalprice", totalprice);
        BookingPayload.put("depositpaid", deposit);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        BookingPayload.put("bookingdates", bookingDates);

        BookingPayload.put("additionalneeds", additionalneeds);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(BookingPayload.toString())
                .post(BASE_URL + "/booking");

        bookingDto = new BookingDto(response.jsonPath().getInt("bookingid"));
        System.out.println("updatedBody after create:" + response.jsonPath().getString("$"));
        originalResponse = response;
    }

    public void updateBooking(String firstname, String lastname, int totalprice, boolean deposit, String checkin, String checkout, String additionalneeds) {
        JSONObject updatePayload = new JSONObject();
        updatePayload.put("firstname", firstname);
        updatePayload.put("lastname", lastname);
        updatePayload.put("totalprice", totalprice);
        updatePayload.put("depositpaid", deposit);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        updatePayload.put("bookingdates", bookingDates);

        updatePayload.put("additionalneeds", additionalneeds);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Cookie", "token=" + tokenDto.getToken()) // Accessing token from DTO
                .body(updatePayload.toString())
                .put(BASE_URL + "/booking/" + bookingDto.getBookingId()); // Accessing bookingId from DTO;
    }

    public void patchBooking(String firstname, String lastname) {
        JSONObject patchPayload = new JSONObject();
        patchPayload.put("firstname", firstname);
        patchPayload.put("lastname", lastname);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Cookie", "token=" + tokenDto.getToken()) // Accessing token from DTO
                .body(patchPayload.toString())
                .patch(BASE_URL + "/booking/" + bookingDto.getBookingId());

        System.out.println("updatedBody after patch: " + response.jsonPath().getString("$"));
//        originalResponse = response;
    }


    public void validateResponseStatus() {
        assertEquals(response.getStatusCode(), 200, "Status code error!");
        assertNotNull(response.getBody(), "Response body should not be null");
        System.out.println("verify body:"+response.getBody().asString());
    }

//    public String getResponseBody() {
//        return response.getBody().asString();
//    }
//
//    public TokenDto getTokenDto() {
//        return tokenDto;
//    }
//
//    public BookingDto getBookingDto() {
//        return bookingDto;
//    }

    public void validateJsonPathValueIsUpdated(String jsonpath1, String jsonpath2) {
//        System.out.println(response.jsonPath().getString("$"));
//        System.out.println(originalResponse.jsonPath().getString("$"));
        ValidationUtils.validateNonIdenticalValues(response, originalResponse, jsonpath1, jsonpath2);
    }

    public void validateJsonPathValueIsSame(String jsonpath1, String jsonpath2) {
        ValidationUtils.validateIdenticalValues(response, originalResponse, jsonpath1, jsonpath2);
    }

    public void deleteBooking() {
        JSONObject deletePayload = new JSONObject();

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + tokenDto.getToken()) // Accessing token from DTO
                .body(deletePayload.toString())
                .delete(BASE_URL + "/booking/" + bookingDto.getBookingId());

        System.out.println("updatedBody after delete:" + response.getBody().asString());
        assertEquals(response.getStatusCode(),201,"status should be 201");
        assertNotNull(response.getBody(),"body should not be empty");

    }
}
