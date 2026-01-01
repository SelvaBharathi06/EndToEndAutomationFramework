package pageObjects.indent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.BasePage;

public class IndentTransporterPage extends BasePage {

    public IndentTransporterPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div[contains(text(),'TRANSPORTER - Select a Transporter')]")
    private WebElement transporterHeader;
    
    By transporterHeaderBy = By.xpath("//div[contains(text(),'TRANSPORTER - Select a Transporter')]");

    @FindBy(xpath = "//div[@class='slider-body second-pane']")
    private WebElement transporterSlider;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement transporterSearchInput;

    //@FindBy(xpath = "//input[@type='radio']")
    //private WebElement transporterRadioButton;

    @FindBy(xpath = "//button[.//span[normalize-space()='Indent']]")
    private WebElement indentButton;

    @FindBy(xpath = "//div[@aria-label='Confirmation']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@role='alert']//span")
    private WebElement indentSuccessMessage;

 
    public String createIndent(String transporterName) {

    	waitForPresenceOfElement(transporterHeaderBy);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", transporterHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", transporterHeader);

        waitForWebElementToAppear(transporterSlider);

        waitForWebElementToBeClickable(transporterSearchInput);
        transporterSearchInput.sendKeys(transporterName);
        

       // waitForWebElementToAppear(transporterRadioButton);
        //((JavascriptExecutor)driver).executeScript("arguments[0].click();", transporterRadioButton);
        
        WebElement bhRadioInput = wait.until( ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Bh']" 
        		+"/ancestor::label[contains(@class,'el-radio')]"+"//input[@type='radio']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", bhRadioInput);

        waitForWebElementToBeClickable(indentButton);
        indentButton.click();

        waitForWebElementToAppear(submitButton);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Confirmation']"
				+ "//button[.//span[normalize-space()='Submit']]")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", submitButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", submitButton);

        waitForWebElementToAppear(indentSuccessMessage);
        String text = indentSuccessMessage.getText();
        String[] indentText = text.split("number");
        String indentId = indentText[1].trim();
        System.out.println(indentId);
        return indentId;
    }
}
