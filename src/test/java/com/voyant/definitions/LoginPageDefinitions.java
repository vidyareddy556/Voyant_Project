package com.voyant.definitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.OutputType;

import java.time.Duration;

public class LoginPageDefinitions {

    public static WebDriver driver;
    public  static Scenario scenario;
    public final static int TIMEOUT = 10;
    public final static int TIMEOUT_5 = 5;
    public final static int TIMEOUT_2 = 2;
    @Before(value = "not @failure")
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }
    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
    }
    @Given("User is on Voyant Login page {string}")
    public void userIsOnVoyantLoginPage(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(10000);
       // i_take_a_screenshot("login page");
    }
    @When("User enters username as {string} and password as {string}")
    public void goToHomePage(String userName, String passWord) {

        // login to application
        driver.findElement(By.name("id")).sendKeys(userName);
        driver.findElement(By.name("pw")).sendKeys(passWord);
        driver.findElement(By.xpath("//*[@type='button']")).click();
        //i_take_a_screenshot("Enter ID and password");
    }
    //Singe Login User Code
    @Given("User is logged with username: {string} and password: {string}")
    public void loginUser(String userName, String passWord) {
        driver.get("https://ca-test.planwithvoyant.com/advisergo/");
        goToHomePage(userName,passWord);
        user_should_be_able_to_login_sucessfully();
    }

    @Then("User should be able to login sucessfully")
    public void user_should_be_able_to_login_sucessfully() {

        WebElement resultsContainer = driver.findElement(By.id("voyantApp"));
        System.out.println(resultsContainer);
        //int results = resultsContainer.findElements(By.tagName("li")).size();
        //i_take_a_screenshot("Successful login page");
        Assert.assertTrue(true);
    }

    /**
     * This enables us to take screenshot from Step Definition
     * @param testCase
     */
    @Given("I take a screenshot {string}")
    public void i_take_a_screenshot(String testCase) {
        // Write code here that turns the phrase above into concrete actions
        HelperDefintions.i_take_a_screenshot(testCase, driver, scenario);
    }
    @After
    public void teardown() {
       // driver.quit();
    }

    @AfterStep
    public  void addScreenshot(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
    }
}