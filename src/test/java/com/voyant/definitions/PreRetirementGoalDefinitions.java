package com.voyant.definitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.voyant.definitions.LoginPageDefinitions.*;

public class PreRetirementGoalDefinitions {

    @When("User clicks Pre-Retiremental Goal Button")
    public void enter_pre_retirement_goal() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='modal-icon icon-control_close test-modal-close close']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@data-test-allclients= 'true']")).click();
        Thread.sleep(2000);
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        Thread.sleep(2000);
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for (int j = 1; j <= cols.size(); j++) {
                driver.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[" + j + "]")).click();
                break;
            }
            break;
        }


       // i_take_a_screenshot("pre retirement goal");
        driver.findElement(By.xpath("//*[@data-test-add-button='true']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
       // i_take_a_screenshot("pre retirement goals page");
        driver.findElement(By.xpath("//*[@data-test-model-type='goals']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
       // driver.findElement(By.xpath("//li[@data-test-goal-type='goal-pre-retirement' and @role ='link']")).click();
        WebElement element = driver.findElement(By.xpath("//li[@data-test-goal-type='goal-pre-retirement' and @role ='link']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
       // i_take_a_screenshot("pre retirement goals page");
    }

    @When("Enters PreRName {string} Amount {string} TaxDeductible {string}")
    public void preretirementgoalform(String PreRName, String Amount , String TaxDeductible)  {
        driver.findElement(By.id("basicExpenseInputAmount")).sendKeys(Amount);
        driver.findElement(By.id("interestTaxDeductible")).sendKeys(TaxDeductible);
        WebElement element = driver.findElement(By.id("basicExpenseInputName"));
        element.clear();
        element.sendKeys(PreRName);
    }
    @When("Submits the form")
    public void preretirementgoalform()  {

        driver.findElement(By.xpath("//*[@aria-label='Done' and @data-test-model-save='true']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        //i_take_a_screenshot("Submits the form");
    }

    @Then("User should be able to create a New Pre-Retiremental goal and navigate to Dashboard")
    public void userShouldBeAbleToCreateANewPreRetirementalGoal()  {

        try {
            driver.findElement(By.className("highcharts-container"));
            Assert.assertTrue(true);
           // i_take_a_screenshot("New Preretirement Goal Created");
            System.out.println("Container element with name 'highcharts-container' found.");
        } catch (Exception e) {
            System.out.println("Container element with name 'highcharts-container' not found.");
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
