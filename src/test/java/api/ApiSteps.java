package api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.ApiEndpoints;

import static org.testng.Assert.*;  // the reason for static: call utilities with ease
//import java.util.List;
//import java.util.Map;

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

    private static final String ENDPOINT = "/tags/v2?type=json&tc=1";
    private static final String BASE_URL = ApiEndpoints.BASE_URL.getUrl();
//    private static final String COLUMN_URL = ApiEndpoints.COLUMN_URL.getUrl();
    private Response response;

    public void setupApiRequest() {
        JSONObject payload = createPayload();
        response = sendRequest(payload);
    }

    private JSONObject createPayload(){
        JSONObject payload = new JSONObject();
        payload.put("v", "v0.1.9");
        payload.put("sr", "https://www.momoshop.com.tw/?srsltid=AfmBOopBTw69YIy3X0-NFlinSwyQiVgnEd60Mb776hDg7oE9ubXwZiXr");
        payload.put("su", "https://www.momoshop.com.tw/main/Main.jsp");
        payload.put("th", "GS00XeaMkLEMxQJpXwzo");

        JSONArray tags = new JSONArray();
        tags.put(new JSONObject().put("eventType", "home"));
        tags.put(new JSONObject()
                .put("eventType", "uid")
                .put("id", "unknown")
                .put("expiryDate", "2026-02-28T15:29:56.880Z"));
        tags.put(new JSONObject()
                .put("eventType", "lid")
                .put("id", "bzS2vvwndPig3UM8Mkev")
                .put("expiryDate", "2026-02-28T15:29:56.880Z"));
        payload.put("tags", tags);

        return payload;
    }

    private Response sendRequest(JSONObject payload){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:135.0) Gecko/20100101 Firefox/135.0")
                .header("Accept","*/*")
                .header("Accept-Language","zh-TW,zh-HK;q=0.8,zh-CN;q=0.5,en-US;q=0.3")
                .header("Accept-Encoding","gzip, deflate, br, zstd")
                .header("Content-Type","application/json")
                .header("Origin","https://www.momoshop.com.tw")
                .header("DNT","1")
                .header("Connection","keep-alive")
                .header("Referer","https://www.momoshop.com.tw/")
                .header("Sec-Fetch-Dest","empty")
                .header("Sec-Fetch-Mode","cors")
                .header("Sec-Fetch-Site","cross-site")
                .header("Priority","u=6")
                .header("TE","trailers")
                .cookie("ar_debug", "1")
                .cookie("c", "3OQ6Y54zacy5xaRqqM2a_V8DOerCn5JrKlPuRawQ8_1740151350958")
                .cookie("g", "3OQ6Y54zacy5xaRqqM2a_1740151350958")
                .cookie("receive-cookie-deprecation","1")
                .cookie("ts", "1740756758")
                .body(payload.toString())
                .post(BASE_URL+ENDPOINT);
//        ar_debug=1; c=3OQ6Y54zacy5xaRqqM2a_V8DOerCn5JrKlPuRawQ8_1740151350958; g=3OQ6Y54zacy5xaRqqM2a_1740151350958; receive-cookie-deprecation=1; ts=1740756758

    }

    public void validateResponse() {
        assertEquals(response.getStatusCode(),200,"status should be 200 ");
        assertNotNull(response.getBody(),"body should not be empty");
        System.out.println(response.getBody().asPrettyString());

    }

    public String getResponseBody(){
        return response.getBody().asString();
    }
}