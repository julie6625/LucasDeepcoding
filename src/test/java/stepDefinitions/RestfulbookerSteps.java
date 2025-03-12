package stepDefinitions;

import api.ApiSteps;
import api.RestfulbookerApiSteps;
import io.cucumber.java.en.*;
import org.json.JSONArray;
import static org.testng.Assert.*;
import static utils.ValidationUtils.*;

public class RestfulbookerSteps {
    private final RestfulbookerApiSteps restfulBookerApiSteps;

    public RestfulbookerSteps() {
        restfulBookerApiSteps = new RestfulbookerApiSteps();
    }

    @Given("I have an authentication token for {string} and {string}")
    public void iHaveAnAuthenticationTokenForAnd(String username,String password){
        restfulBookerApiSteps.createtoken(username,password);
    }

    @When("I create a booking with firstname {string}, lastname {string}, totalprice {int}, depositpaid {string}, checkin {string}, checkout {string}, and additionalneeds {string}")
    public void iCreateABookingWithFirstnameLastnameTotalpriceDepositpaidCheckinCheckoutAndAdditionalneeds(String firstname, String lastname, int totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        boolean deposit = Boolean.parseBoolean(depositpaid);
        restfulBookerApiSteps.createBooking(firstname, lastname, totalprice, deposit, checkin, checkout, additionalneeds);
    }

    @And("I update the booking with firstname {string}, lastname {string}, totalprice {int}, depositpaid {string}, checkin {string}, checkout {string}, and additionalneeds {string}")
    public void iUpdateABookingWithFirstnameLastnameTotalpriceDepositpaidCheckinCheckoutAndAdditionalneeds(String firstname, String lastname, int totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        boolean deposit = Boolean.parseBoolean(depositpaid);
        restfulBookerApiSteps.updateBooking(firstname, lastname, totalprice, deposit, checkin, checkout, additionalneeds);
    }

    @And("I Patch the booking with firstname {string}, lastname {string}")
    public void IPatchthebookingwithfirstnamelastname(String firstname,String lastname){
        restfulBookerApiSteps.patchBooking(firstname, lastname);
    }

    @Then("I should receive a successful restful-booker api response")
    public void iShouldReceiveASuccessfulRestfulBookerApiResponse() {
        restfulBookerApiSteps.validateResponseStatus();
    }

    @Then("The updated response jsonpath value {string} should be different from original response jsonpath value {string}")
    public void validateUpdatedJsonpathValueIsDifferent(String jsonpath1, String jsonpath2) {
        restfulBookerApiSteps.validateJsonPathValueIsUpdated(jsonpath1, jsonpath2);
    }

    @Then("The updated response jsonpath value {string} should be same as original response jsonpath value {string}")
    public void validateUpdatedJsonpathValueIsSame(String jsonpath1, String jsonpath2) {
        restfulBookerApiSteps.validateJsonPathValueIsSame(jsonpath1, jsonpath2);
    }

    @And("I delete the booking")
    public void iDeleteTheBooking() {
        restfulBookerApiSteps.deleteBooking();
    }
}
