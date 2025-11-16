package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    
    // Locators
    private By checkoutButton = By.cssSelector("button[data-test='proceed-1']");
    private By cartItems = By.cssSelector("[data-test='cart-item']");
    private By emptyCartMessage = By.cssSelector("[data-test='empty-cart']");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public void proceedToCheckout() {
        click(checkoutButton);
    }
    
    public boolean isCartEmpty() {
        return isElementPresent(emptyCartMessage) || 
               driver.findElements(cartItems).isEmpty();
    }
    
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
}

