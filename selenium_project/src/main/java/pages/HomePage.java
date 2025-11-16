package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    
    // Locators
    private By firstProductLink = By.cssSelector("img.card-img-top[alt='Combination Pliers']");
    private By cartIcon = By.cssSelector("a[href*='/cart']");
    private By signInButton = By.cssSelector("a[data-test='nav-sign-in']");

    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToHomePage() {
        driver.get("https://practicesoftwaretesting.com/");
    }
    
    public void clickSignInButton() {
        click(signInButton);
    }
    
    public void clickFirstProduct() {
        click(firstProductLink);
    }
    
    public void clickCartIcon() {
        click(cartIcon);
    }
}

