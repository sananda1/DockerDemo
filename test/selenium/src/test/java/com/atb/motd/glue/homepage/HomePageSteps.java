package com.atb.motd.glue.homepage;

import com.atb.motd.glue.CommonSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageSteps extends CommonSteps {

  private int sleep = 300;

    @Given("^the user is on the message of the day homepage$")
    public void userIsOnMessageOfTheDayHomepage() throws Exception {

        driver.get(siteURL);
        verifyTitle("Message of the Day", 5);
        Thread.sleep(sleep);
    }

    @Then("^the user sees the message of the day$")
    public void userSeesTheMessageOfTheDay() throws Exception {

        verifyVisibilityById("messageOfTheDay", 5);
        Thread.sleep(sleep);
    }

    @When("^the user enters a previous date that is (\\d+)/(\\d+)/(\\d+)$")
    public void userEntersAPreviousDate(Integer month, Integer day, Integer year) throws Exception {

        WebElement dateInput = findElementWhenClickable(By.id("datepicker"), 5);
        dateInput.sendKeys(month + "/" + day + "/" + year);
        Thread.sleep(sleep);

        // dismiss the pop-up calendar and wait for it to no longer be visible
        // if we don't wait, the calendar dialog is over the button and we can't click on it.
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        verifyElementNotVisible("ui-datepicker-div", 5000);
        Thread.sleep(sleep);
    }

    @And("^the user clicks the get message button$")
    public void userClicksTheGetMessageButton() throws Exception {

        WebElement messageButton = findElementWhenClickable(By.id("messageBtn"), 5);
        messageButton.click();
        Thread.sleep(sleep);
    }

    @Then("^the user sees the message of the day for a (.*)$")
    public void userSeesTheMessageOfTheDayForADay(String dayOfWeek) throws Exception {

        verifyVisibilityByIdAndText(dayOfWeek, "messageByDateMessage", 5);
        Thread.sleep(sleep);
    }

}
