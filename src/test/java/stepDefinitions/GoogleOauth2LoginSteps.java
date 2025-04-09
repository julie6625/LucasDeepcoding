package stepDefinitions;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.sun.net.httpserver.HttpServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;

public class GoogleOauth2LoginSteps {

    private String code;
    private HttpServer server;

    private GoogleAuthorizationCodeFlow flow;

    private final String CLIEND_ID = "";
    private final String CLIEND_SECRET = "";

    private final String REDIRECT_URI = "http://localhost:8080/oauth2callback";

    private Credential credential;

    @Given("the navigates to Google Oauth2 login page")
    public void user_navigates_to_Google_Oauth2_login() throws IOException {
        // step.1 Start local server to wait google redirect
        server = HttpServer.create(new InetSocketAddress(8080),0);

        //Step.2 Set redirect url
        server.createContext("/oauth2callback", exchange -> {
            URI requestURI = exchange.getRequestURI();

            String query = requestURI.getQuery();
            if (query != null && query.contains("code=")){

                code = query.split("code=")[1].split("&")[0];
                String response = "Google OAuth Login Success! You may close this tab";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }else {
                code = query.split("code=")[1].split("&")[0];
                String response = "Missing authorization code";
                exchange.sendResponseHeaders(400, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }

        });
        server.setExecutor(null);
        System.out.println("Starting Http server on port 8080...");
        server.start();
        System.out.println("Server started successfully.");

        flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                CLIEND_ID,
                CLIEND_SECRET,
                List.of("openid", "email", "profile")).setAccessType("offline").build();
        String authorizationURL = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Please open this URL in your browser: "+ authorizationURL);
    }

    @When("the user complete the login with valid Google credentials")
    public void user_completes_login() throws InterruptedException, IOException {
        System.out.println("Waiting for Google authorization");
        while (code == null){
            Thread.sleep(500);
        }
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        credential = flow.createAndStoreCredential(tokenResponse, "user");
        if (server != null){
            server.stop(0);
        }
    }

    @Then("the user should receive an access token")
    public void user_should_receive_access_token(){
        assert  credential != null;
        assert  credential.getAccessToken() != null;
        System.out.println("Access Token: " + credential.getAccessToken());
    }
}
