package com.voyant.definitions;

import com.voyant.runner.CucumberRunnerTests;
import io.cucumber.java.*;
import io.cucumber.java.en.Then;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public  class HelperDefintions extends CucumberRunnerTests {

    @Then("^I take a screenshot$")
    public static void i_take_a_screenshot(String testcase, WebDriver driver, Scenario scenario) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", testcase);

    }

    public static void i_take_a_screenshot(WebDriver driver, Scenario scenario) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "image");

    }

}
