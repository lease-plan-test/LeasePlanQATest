package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import support.Utilities;
import io.restassured.response.Response;

public class ApiSteps {
    private final String baseUrl = Utilities.getAPIConfigData("base.url");
    private String apiKey = Utilities.getAPIConfigData("api.key");
    private final JSONObject ipObject = Utilities.getRandomIP();;
    private String ip = (String) ipObject.get("ip");;
    RequestSpecification request = RestAssured.given();
    private Response response;

    @Given("I setup request with a valid IP")
    public void i_setup_request_with_a_ip() {
        request.baseUri(baseUrl)
                .basePath(ip)
                .queryParam("access_key", apiKey);
    }

    @Given("I setup request with {string} IP")
    public void i_setup_request_with_ip(String type) {
        ip = Utilities.getErroneousIP(type);
        request.baseUri(baseUrl)
                .basePath(ip)
                .queryParam("access_key", apiKey);
    }

    @Given("I setup a request with {string} key")
    public void i_setup_a_request_with_key(String keyType) {
        apiKey = Utilities.getErroneousKey(keyType);
        request.baseUri(baseUrl)
                .basePath(ip)
                .queryParam("access_key", apiKey);
    }

    @When("I make api call")
    public void i_make_api_call() {
        response = request.when().get();
    }

    @Then("I verify response code to be {int}")
    public void i_verify_response_code_to_be(int code) {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @Then("I verify response to be {string}")
    public void i_verify_location_in_response_to_be(String type) {
        if (type.equals("valid")) {
            Assert.assertEquals(ipObject.get("ip"), response.jsonPath().get("ip"));
            Assert.assertEquals(ipObject.get("continent_name"), response.jsonPath().get("continent_name"));
            Assert.assertEquals(ipObject.get("country_name"), response.jsonPath().get("country_name"));
            Assert.assertEquals(ipObject.get("city"), response.jsonPath().get("city"));
            Assert.assertEquals(ipObject.get("calling_code"), response.jsonPath().get("location.calling_code"));
            Assert.assertEquals(ipObject.get("is_eu"), response.jsonPath().get("location.is_eu"));
        } else {
            Assert.assertNull(response.jsonPath().get("continent_name"));
            Assert.assertNull(response.jsonPath().get("country_name"));
            Assert.assertNull(response.jsonPath().get("city"));
            Assert.assertNull(response.jsonPath().get("location.calling_code"));
            Assert.assertNull(response.jsonPath().get("location.is_eu"));
        }
    }

    @Then("I verify error response message to be {string}")
    public void i_verify_error_response_to_be(String errorMessage) {
        Assert.assertEquals(errorMessage, response.jsonPath().get("error.type"));
    }

}
