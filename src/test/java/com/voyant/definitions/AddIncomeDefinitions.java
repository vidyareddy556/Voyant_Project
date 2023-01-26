package com.voyant.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
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
import java.util.Map;

import static com.voyant.definitions.LoginPageDefinitions.*;

public class AddIncomeDefinitions {
    public  Scenario scenario;
    @Before(value = "not @failure")
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }
    @When("User clicks income Button for a client")
    public void user_clicks_income_button() throws InterruptedException {

        driver.findElement(By.xpath("//button[@class='modal-icon icon-control_close test-modal-close close']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@data-test-allclients= 'true']")).click();
        Thread.sleep(1000);
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        Thread.sleep(2000);
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for (int j = 1; j <= cols.size(); j++) {
                //TODO: can select user based on identifier or selector
                driver.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[" + j + "]")).click();
                break;
            }
            break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.findElement(By.xpath("//*[@data-test-add-button='true']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
       // i_take_a_screenshot("Income Button");
    }

    @When("clicks Employment Button")
    public void click_employment_button()  {

        driver.findElement(By.xpath("//div//*[@class='icon icon-nav_employment add-button-icon']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

        WebElement element = driver.findElement(By.xpath("//li[@data-test-model-category='caEmployment'and @role ='link']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        //i_take_a_screenshot("Employment Button");

    }
    @When("Enters Employment Information")
    public void enter_employment_form(DataTable dataTable) throws Throwable {

        Map<String, String> formData = dataTable.asMap(String.class, String.class);
        driver.findElement(By.id("employmentInputName")).sendKeys( formData.get("employmentInputName"));
        driver.findElement(By.id("employmentInputEmploymentSource")).sendKeys(formData.get("Source"));
        driver.findElement(By.id("employmentInputSalary")).sendKeys(formData.get("Salary"));
        driver.findElement(By.id("employmentInputBonus")).sendKeys(formData.get("BonusesOrCommissions"));
        driver.findElement(By.id("employmentInputBenefitsInKind")).sendKeys(formData.get("TaxableEmployeeBenefits"));
        //i_take_a_screenshot("Employment Information");
    }

    @When("Submits the Employment details Form")
    public void submits_employment_details_form()  {

        driver.findElement(By.xpath("//button[@class='btn btn-primary edit-modal-button ' and @data-test-model-save='true']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

    }
    @Then("User should be able to successfully add Income and navigate to Dashboard")
    public void user_should_able_to_add_income()  {

        try {
            driver.findElement(By.className("highcharts-container"));
            Assert.assertTrue(true);
            //i_take_a_screenshot("added income");
            System.out.println("Container element with name 'highcharts-container' found.");
        } catch (Exception e) {
            Assert.fail();
            System.out.println("Container element with name 'highcharts-container' not found.");
        }
    }


    @After
    public void teardown() {
        driver.quit();
    }
}
