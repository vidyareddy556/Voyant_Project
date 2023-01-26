package com.voyant.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "not @ignore", features = {"src/test/resources/features/"}, glue = {"com.voyant.definitions"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary"},
        monochrome = true
)

public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}