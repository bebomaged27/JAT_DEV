package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    
    // Locators
    private By addToCartButton = By.id("btn-add-to-cart");
    private By cartIcon = By.cssSelector("a[data-test='nav-cart']");
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    public void addToCart() {
        click(addToCartButton);
    }
    
    public void goToCart() {
        click(cartIcon);
    }
}

