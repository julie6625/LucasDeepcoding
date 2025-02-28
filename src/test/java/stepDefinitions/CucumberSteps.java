package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
}
