package stepDefinitions;

import stepDefinitions.ApiSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import static org.testng.Assert.*;

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
    public void repsonse_sholud_have_expected_types(){

    }
}
