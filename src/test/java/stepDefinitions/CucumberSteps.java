package stepDefinitions;

import api.ApiSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import static org.testng.Assert.*;
import static utils.ValidationUtils.*;

public class CucumberSteps {
    private final ApiSteps apiSteps;

    public CucumberSteps(){
        apiSteps =new ApiSteps();
    }

    @When("I send creativecdn api")
    public void I_send_creativecdn_api(){
        apiSteps.setupApiRequest();
    }

    @Then("I should receive a successful api response")
    public void i_should_receive_a_successful_api_response() {
        apiSteps.validateResponse();
    }

    @And("response should contain {int} items")
    public void response_should_contain_int_items(int responseCount){
        JSONArray responseArray = new JSONArray(apiSteps.getResponseBody());
        assertEquals(responseArray.length(), responseCount, "Response should contain" + responseCount + "items");
    }

    @And("repsonse sholud have expected types")
    public void repsonse_sholud_have_expected_types() {
        JSONArray responseArray = new JSONArray(apiSteps.getResponseBody());
        assertEquals(responseArray.getJSONObject(0).getString("type"), "IMG",
                "First item should have type 'IMG'");
        assertEquals(responseArray.getJSONObject(1).getString("type"), "IFRAME",
                "Second item should have type 'IFRAME'");
        assertEquals(responseArray.getJSONObject(2).getString("type"), "IFRAME",
                "Third item should have type 'IFRAME'");
    }


    @And("repsonse sholud have expected prefix URL")
    public void repsonse_sholud_have_expected_prefix_URL(){
        JSONArray responseArray = new JSONArray(apiSteps.getResponseBody());

        String firstURL = responseArray.getJSONObject(0).getString("url");
        String secondURL = responseArray.getJSONObject(1).getString("url");
        String thridURL = responseArray.getJSONObject(2).getString("url");

        validateValuePrefix(firstURL, "https://");
        validateValuePrefix(secondURL, "https://asia.creativecdn.com/ig-membership?ntk=C8glJYLb2fUsXvsdmM5V_UnXAc2-yLHeCIqDCv7I9rp50J95c7E28qtscCx4j59n309UTOytQZYnCr5Les1kVzb6-XMishrW0KG5PSrCr2c");
        validateValuePrefix(thridURL, "https://asia.creativecdn.com/topics-membership?ntk=bzfIakTfSwtST_7CopkvmKn3nkOoRF_uXMUd2UvRUx-Y_8IIqL-pC-fOAjnHOkpinuW2_BvbTew3ZIvY756DqNRJjRxV8PL10zE7BNjTskY");

    }
}
