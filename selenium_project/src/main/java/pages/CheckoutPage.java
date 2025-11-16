package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class CheckoutPage extends BasePage {
    
    // Billing details locators - only 5 fields: street, city, state, country, post_code
    private By addressInput = By.id("street");
    private By cityInput = By.id("city");
    private By stateInput = By.id("state");
    private By countryInput = By.id("country");
    private By postalCodeInput = By.id("postal_code");
    
    // Shipping details locators (if different from billing)
    private By shippingAddressInput = By.id("street");
    private By shippingPostalCodeInput = By.id("postal_code");
    private By shippingCityInput = By.id("city");
    private By shippingCountryInput = By.id("country");
    private By shippingStateInput = By.id("state");
    
    // Checkbox and button locators
    private By sameAsBillingCheckbox = By.id("same_as_billing");
    private By placeOrderButton = By.cssSelector("button[data-test='proceed-3']");
    
    // Success message locator
    private By successMessage = By.cssSelector("[data-test='payment-success-message']");
    
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Wait for checkout form to be ready
     */
    @Step("Wait for checkout form to be ready")
    public void waitForCheckoutFormReady() {
        // Wait for at least one billing field to be present and visible
        wait.until(ExpectedConditions.presenceOfElementLocated(addressInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput));
    }
    
    @Step("Fill billing details")
    public void fillBillingDetails(Map<String, String> data) {
        // Wait for checkout form to be ready before filling
        waitForCheckoutFormReady();
        
        // Fill only the 5 required fields: street, city, state, country, post_code
        wait.until(ExpectedConditions.elementToBeClickable(addressInput));
        sendKeys(addressInput, data.get("street"));
        
        wait.until(ExpectedConditions.elementToBeClickable(cityInput));
        sendKeys(cityInput, data.get("city"));
        
        wait.until(ExpectedConditions.elementToBeClickable(stateInput));
        sendKeys(stateInput, data.get("state"));
        
        // Fill country as input field
        wait.until(ExpectedConditions.elementToBeClickable(countryInput));
        sendKeys(countryInput, data.get("country"));
        
        wait.until(ExpectedConditions.elementToBeClickable(postalCodeInput));
        sendKeys(postalCodeInput, data.get("post_code"));
    }
    
    @Step("Fill shipping details")
    public void fillShippingDetails(Map<String, String> data) {
        // Check if same as billing checkbox exists and is checked
        if (isElementPresent(sameAsBillingCheckbox)) {
            WebElement checkbox = driver.findElement(sameAsBillingCheckbox);
            if (!checkbox.isSelected()) {
                // If not checked, fill shipping details separately
                if (isElementPresent(shippingAddressInput)) {
                    // Fill only the 5 required fields: street, city, state, country, post_code
                    sendKeys(shippingAddressInput, data.get("street"));
                    sendKeys(shippingCityInput, data.get("city"));
                    sendKeys(shippingStateInput, data.get("state"));
                    
                    // Fill country as input field
                    wait.until(ExpectedConditions.elementToBeClickable(shippingCountryInput));
                    sendKeys(shippingCountryInput, data.get("country"));
                    
                    sendKeys(shippingPostalCodeInput, data.get("post_code"));
                }
            }
        } else {
            // If no checkbox, shipping fields might be same as billing or separate
            // Try to fill shipping fields if they exist
            if (isElementPresent(shippingAddressInput)) {
                // Fill only the 5 required fields: street, city, state, country, post_code
                sendKeys(shippingAddressInput, data.get("street"));
                sendKeys(shippingCityInput, data.get("city"));
                sendKeys(shippingStateInput, data.get("state"));
                
                // Fill country as input field
                wait.until(ExpectedConditions.elementToBeClickable(shippingCountryInput));
                sendKeys(shippingCountryInput, data.get("country"));
                
                sendKeys(shippingPostalCodeInput, data.get("post_code"));
            }
        }
    }
    
    @Step("Place the order")
    public void placeOrder() {
        click(placeOrderButton);
    }
    
    @Step("Verify order success message is displayed")
    public boolean isOrderSuccessMessageDisplayed() {
        return isElementPresent(successMessage);
    }
    
    public String getSuccessMessage() {
        if (isOrderSuccessMessageDisplayed()) {
            return getText(successMessage);
        }
        return "";
    }
}

