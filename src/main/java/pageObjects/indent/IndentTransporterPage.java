package pageObjects.indent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.BasePage;

public class IndentTransporterPage extends BasePage {

    public IndentTransporterPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div[contains(text(),'TRANSPORTER - Select a Transporter')]")
    private WebElement transporterHeader;

    @FindBy(xpath = "//div[@class='slider-body second-pane']")
    private WebElement transporterSlider;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement transporterSearchInput;

    @FindBy(xpath = "//input[@type='radio']")
    private WebElement transporterRadioButton;

    @FindBy(xpath = "//button[.//span[normalize-space()='Indent']]")
    private WebElement indentButton;

    @FindBy(xpath = "//div[@aria-label='Confirmation']//button[.//span[normalize-space()='Submit']]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@role='alert']//span")
    private WebElement indentSuccessMessage;

 
    public String createIndent(String transporterName) {

        waitForWebElementToAppear(transporterHeader);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", transporterHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", transporterHeader);

        waitForWebElementToAppear(transporterSlider);

        waitForWebElementToBeClickable(transporterSearchInput);
        transporterSearchInput.sendKeys(transporterName);

        waitForWebElementToAppear(transporterRadioButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", transporterRadioButton);

        waitForWebElementToBeClickable(indentButton);
        indentButton.click();

        waitForWebElementToBeClickable(submitButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", submitButton);

        waitForWebElementToAppear(indentSuccessMessage);
        return indentSuccessMessage.getText();
    }
}
