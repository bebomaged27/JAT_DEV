package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocatorHelper {
    
    /**
     * Try multiple locators and return the first found element
     */
    public static WebElement findElementWithAlternatives(WebDriver driver, By... locators) {
        for (By locator : locators) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty()) {
                return elements.get(0);
            }
        }
        throw new RuntimeException("None of the alternative locators found an element");
    }
    
    /**
     * Check if any of the alternative locators is present
     */
    public static boolean isAnyElementPresent(WebDriver driver, By... locators) {
        for (By locator : locators) {
            if (!driver.findElements(locator).isEmpty()) {
                return true;
            }
        }
        return false;
    }
}

