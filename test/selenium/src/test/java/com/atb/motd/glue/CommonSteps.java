package com.atb.motd.glue;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Common behaviour shared across test automation project
 */
public class CommonSteps {

    protected WebDriver driver = Hooks.getWebDriver();
    protected String siteURL = Hooks.getSiteURL();

    /**
     * Verify the title text after an explicit wait for the page to render.
     * This method utilizes the web driver explicit wait capabilities which is much more
     * efficient than using Thread.sleep as it polls the DOM and resumes execution immediately
     * upon the element becoming visible.
     *
     * @param expectedTitle the expected title text.
     * @param waitSeconds the time to wait (seconds) for the title visible.
     */
    protected void verifyTitle(final String expectedTitle, final int waitSeconds) {

        (new WebDriverWait(driver, waitSeconds)).until(ExpectedConditions.titleIs(expectedTitle));
    }

    /**
     * Find an element, by ID, after it becomes visible.
     * This method utilizes the web driver explicit wait capabilities which is much more
     * efficient than using Thread.sleep as it polls the DOM and resumes execution immediately
     * upon the element becoming visible.
     *
     * @param elementId the element id.
     * @param waitSeconds the time to wait (seconds) for the element to become visible.
     */
    protected void verifyVisibilityById(final String elementId, final int waitSeconds) {

        (new WebDriverWait(driver, waitSeconds)).until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
    }

    /**
     * Verify the visibility of an element by ID and verify its text value.
     * This method utilizes the web driver explicit wait capabilities which is much more
     * efficient than using Thread.sleep as it polls the DOM and resumes execution immediately
     * upon the element becoming visible.
     *
     * @param expectedElementText the expected text value of the element.
     * @param elementId the element id.
     * @param waitSeconds the time to wait (seconds) for the element to become visible.
     */
    protected void verifyVisibilityByIdAndText(final String expectedElementText, final String elementId, final int waitSeconds) {

        // find the element
        WebElement element = (new WebDriverWait(driver, waitSeconds)).until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));

        // verify the text
        (new WebDriverWait(driver, waitSeconds)).until(ExpectedConditions.textToBePresentInElement(element, expectedElementText));
    }

    /**
     * Find an element, waiting for it to become visible and clickable, by its Locator By object.
     * This method utilizes the web driver explicit wait capabilities which is much more
     * efficient than using Thread.sleep as it polls the DOM and resumes execution immediately
     * upon the element becoming visible.
     *
     * @param elementLocator the element locater.
     * @param waitSeconds the time to wait (seconds) for the element to become visible.
     */
    protected WebElement findElementWhenClickable(final By elementLocator, final int waitSeconds) {
        WebElement element = (new WebDriverWait(driver, waitSeconds)).until(ExpectedConditions.elementToBeClickable(elementLocator));
        return element;
    }

    /**
     * Verify that an element is not visible.
     * This method utilizes the web driver explicit wait capabilities which is much more
     * efficient than using Thread.sleep as it polls the DOM and resumes execution immediately
     * upon the element becoming invisible.
     *
     * @param elementId the element id.
     * @param waitSeconds the time to wait (seconds) for the element to become invisible.
     */
    protected Boolean verifyElementNotVisible(final String elementId, final int waitSeconds) {

        Boolean visible = (new WebDriverWait(driver, waitSeconds)).until(ExpectedConditions.invisibilityOfElementLocated(By.id(elementId)));
        return visible;
    }
}
