package com.automation.steps;

import com.automation.demo.CreateBookingPojo;
import com.automation.demo.CreateTokenPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.Random;

public class RequestSteps {
    @Given("user wants to call {string} end point")
    public void user_wants_to_call_end_point(String endPoint) {
        if (endPoint.contains("@id")) {
            String BookingId = ConfigReader.getConfigValue("booking.id");
            endPoint = endPoint.replace("@id", BookingId);
        }
        RestAssuredUtils.setEndPoint(endPoint);

    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        if (value.contains("@token")) {
            value = value.replace("@token", ConfigReader.getConfigValue("api.token"));
        }
        RestAssuredUtils.setHeader(key, value);
    }

    @And("set request body from the file {string}")
    public void setRequestBodyFromTheFile(String fileName) {
        RestAssuredUtils.setBody(fileName);
    }

    @When("user perform post call")
    public void user_perform_post_call() {
        RestAssuredUtils.post();

    }

    @When("user perform get call")
    public void userPerformGetCall() {
        RestAssuredUtils.get();
    }

    @And("user perform put call")
    public void userPerformPutCall() {
        RestAssuredUtils.put();
    }


    @And("set request body from the file {string} with random price")
    public void setRequestBodyFromTheFileWithRandomPrice(String fileName) throws JsonProcessingException {
        String jsonFolderPath=ConfigReader.getConfigValue("json.folder.path");
        String jsonBody=RestAssuredUtils.getDataFromFile(jsonFolderPath+fileName);
        ObjectMapper om=new ObjectMapper();
        CreateBookingPojo createBookingPojo=om.readValue(jsonBody, CreateBookingPojo.class);

        int price =new Random().nextInt(1000);
        createBookingPojo.setTotalprice(price);
        RestAssuredUtils.setBodyusingPogo(createBookingPojo);
    }

    @And("set request body from the file {string} with random name")
    public void setRequestBodyFromTheFileWithRandomName(String fileName) throws JsonProcessingException {
        String jsonFolderPath=ConfigReader.getConfigValue("json.folder.path");
        String jsonBody=RestAssuredUtils.getDataFromFile(jsonFolderPath+fileName);
        ObjectMapper om=new ObjectMapper();
        CreateBookingPojo createBookingPojo=om.readValue(jsonBody, CreateBookingPojo.class);

        String fname="qwertyuiopasdfghjklzxcvbnm";
        String firstname="";
        for (int i = 0; i <5; i++) {
            int pos=new Random().nextInt(18);
            firstname=firstname+fileName.charAt(pos);
        }
        createBookingPojo.setFirstname(firstname);
        RestAssuredUtils.setBodyusingPogo(createBookingPojo);

    }

    @And("set request body from the file {string} with username {string} and password {string}")
    public void setRequestBodyFromTheFileWithUsernamAndPassword(String fileName, String username, String password) throws JsonProcessingException {
        String jsonFolderPath=ConfigReader.getConfigValue("json.folder.path");
        String jsonBody=RestAssuredUtils.getDataFromFile(jsonFolderPath+fileName);
        ObjectMapper om=new ObjectMapper();
        CreateTokenPojo createTokenPojo =om.readValue(jsonBody, CreateTokenPojo.class);

        createTokenPojo.setUsername(username);
        createTokenPojo.setPassword(password);
        RestAssuredUtils.setBodyusingPogo(createTokenPojo);
    }
}
