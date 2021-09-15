package Selenium_hw5.steps;

import io.cucumber.java.en.Given;

public class GivenStep extends AbstractStep {

    @Given("I open JDI GitHub site")
    public void openHomePage() {
        homePage.open(properties.getString("baseURL"));
    }


    @Given("I login as user {string}")
    public void performLogin(String user) {
        homePage.getHeader().signIn(properties.getString("login"), properties.getString("password"));
    }
}
