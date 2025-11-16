package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PaymentPage extends BasePage {
    
    // Payment page locators
    private By paymentMethodDropdown = By.id("payment-method");
    private By confirmButton = By.cssSelector("button[data-test='finish']");
    
    public PaymentPage(WebDriver driver) {
        super(driver);
    }
    
    @Step("Select Cash on Delivery payment method")
    public void selectCashOnDelivery() {
        Select paymentDropdown = new Select(waitForElement(paymentMethodDropdown));
        paymentDropdown.selectByVisibleText("Cash on Delivery");
    }
    
    @Step("Click confirm button to complete payment")
    public void clickConfirm() {
        click(confirmButton);
    }
    
    @Step("Complete payment with Cash on Delivery")
    public void completePayment() {
        selectCashOnDelivery();
        clickConfirm();
    }
}

