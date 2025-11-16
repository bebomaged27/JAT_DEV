package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    
    // Locators
    private By continueAsGuestButton = By.cssSelector("a[data-bs-toggle='tab'][href='#guest-tab']");
    private By emailInput = By.id("guest-email");
    private By firstNameInput = By.id("guest-first-name");
    private By lastNameInput = By.id("guest-last-name");
    private By passwordInput = By.id("password");
    private By submitButton = By.cssSelector("input[data-test='guest-submit']");
    private By proceedToCheckoutButton = By.cssSelector("button[data-test='proceed-2-guest']");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }
    
    public void enterFirstName(String firstName) {
        sendKeys(firstNameInput, firstName);
    }
    
    public void enterLastName(String lastName) {
        sendKeys(lastNameInput, lastName);
    }
    
    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }
    
    public void clickContinueAsGuest() {
        click(continueAsGuestButton);
    }
    
    public void clickLogin() {
        click(submitButton);
    }
    
    public void login(String email, String password) {
        clickContinueAsGuest();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
    
    @Step("Continue as guest with email: {0}, name: {1} {2}")
    public void continueAsGuest(String email, String firstName, String lastName) {
        clickContinueAsGuest();
        // Wait for guest form to be visible
        wait.until(ExpectedConditions.presenceOfElementLocated(emailInput));
        enterEmail(email);
        enterFirstName(firstName);
        enterLastName(lastName);
        clickLogin();
        // Wait for "Proceed to checkout" button and click it
        WebElement proceedToCheckoutBtn = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutBtn.click();
        // Wait for page to transition - wait for checkout form elements to start appearing
        // This ensures the page has navigated to checkout form
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("street")));
    }
}

