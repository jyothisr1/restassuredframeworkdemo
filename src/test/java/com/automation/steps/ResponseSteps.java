package com.automation.steps;

import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ResponseSteps {
    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @And("verify list is not null")
    public void verifyListIsNotNull() {
        Assert.assertFalse(RestAssuredUtils.getResponse().toString().isEmpty());

//        System.out.println(RestAssuredUtils.get().getBody().toString().charAt(1));
    }

    @And("user stores created booking id into {string};")
    public void userStoresCreatedBookingIdInto(String key) {
        ConfigReader.setConfig(key,RestAssuredUtils.getResponse().jsonPath().getString("bookingid"));

    }

    @And("verify booking id is not empty")
    public void verifyBookingIdIsNotEmpty() {
        String bookingId=RestAssuredUtils.getResponse().jsonPath().getString("bookingid");
        Assert.assertTrue(!bookingId.isEmpty());
    }


    @And("store token value to {string}")
    public void storeTokenValueTo(String key) {
        String token=RestAssuredUtils.getResponse().jsonPath().getString("token");
        ConfigReader.setConfig(key,token);
    }

    @And("verify response message is {string}")
    public void verifyResponseMessageIs(String message) {
        Assert.assertEquals(message,RestAssuredUtils.getResponse().jsonPath().getString("reason"));
    }
}
