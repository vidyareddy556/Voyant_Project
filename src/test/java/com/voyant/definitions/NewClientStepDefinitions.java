package com.voyant.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

import static com.voyant.definitions.LoginPageDefinitions.*;

public class NewClientStepDefinitions {

    @When("Enter New client form details")
    public void enter_new_client_form_details(DataTable dataTable) {

        Map<String, String> formData = dataTable.asMap(String.class, String.class);
        driver.findElement(By.id("newInputFirst")).sendKeys(formData.get("FirstName"));
        driver.findElement(By.id("newInputLast")).sendKeys(formData.get("LastName"));
        driver.findElement(By.id("newPersonInputBirthday")).sendKeys(formData.get("BirthYear"));
        WebElement element = driver.findElement(By.xpath("//*[@aria-label='MM Month']"));
        element.clear();
        element.sendKeys(formData.get("MonthOfPlan"));
        element = driver.findElement(By.xpath("//*[@aria-label='DD Day']"));
        element.clear();
        element.sendKeys(formData.get("DateOfPlan"));
        element = driver.findElement(By.xpath("//*[@aria-label='YYYY Year']"));
        element.clear();
        element.sendKeys(formData.get("YearOfPlan"));
        driver.findElement(By.id("alreadyRetiredDropdown")).sendKeys(formData.get("isRetired"));
        element = driver.findElement(By.id("retirementAge"));
        element.clear();
        element.sendKeys(formData.get("RetirementAge"));
        //i_take_a_screenshot("Entered Form Data");
    }

    @When("User clicks create New Client form")
    public void user_clicks_create_newClient_form() throws InterruptedException {
        Thread.sleep(10000);
        // Write code here that turns the phrase above into concrete actions
        try {
           WebElement element = driver.findElement(By.xpath("//*[@type='button']"));
            element.click();
        } catch (Exception e) {
            System.out.println(" element with name 'highcharts-container' not found.");
            Thread.sleep(10000);
        }
        //driver.findElement(By.xpath("//*[@type='button']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_2));
        driver.findElement(By.xpath("//*[@aria-label='Add model']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
       // i_take_a_screenshot("New Client Form ");
    }
    @When("When User submits form")
    public void when_user_submit_form() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//*[@aria-label='Done']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
    }

    @Then("User should be able to create a New Client Record")
    public void user_should_be_able_to_Create_NewClient() {

        WebElement resultsContainer = driver.findElement(By.xpath("//*[@class='icon icon-control_person']"));
        System.out.println(resultsContainer+"New Client Created Successful");
        //i_take_a_screenshot("New Client Created ");

    }

    @After
    public void teardown() {
        driver.quit();
    }
}


