package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class ApiSteps {
    @Given("I start the test")
    public void i_start_the_test() {
        System.out.println("Step: I start the test.");
    }
    @When("I execute the step")
    public void i_execute_the_step() {
        System.out.println("Step: I execute the step.");
    }
    @Then("I should see the result")
    public void i_should_see_the_result() {
        System.out.println("Step: I should see the result.");
    }
}