package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@type='text']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(css = "div.button-style button")
    private WebElement loginButton;
 
    public void goToLoginPage() {
        driver.get("https://qa.pandostaging.in/login/azure");
    }
  
    public LandingPage login(String username, String password) {

        waitForWebElementToBeClickable(usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new LandingPage(driver);
    }
}
