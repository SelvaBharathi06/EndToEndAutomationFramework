package pageObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.BasePage;

public class DepotPage extends BasePage {

    public DepotPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@class,'header_pbtn')]")
    private WebElement addDepotButton;

    @FindBy(css = "input[placeholder='Enter Depot Name']")
    private WebElement depotName;
    
    @FindBy(css = "input[placeholder='Enter Reference Id']")
    private WebElement depotReferenceId;

    @FindBy(css = "input[placeholder='Enter Depot Short Code']")
    private WebElement depotShortCode;

    @FindBy(css = "input[placeholder='Enter GSTIN']")
    private WebElement gstin;

    @FindBy(css = "input[placeholder='Enter Address']")
    private WebElement address;

    @FindBy(css = "input[placeholder='Enter City']")
    private WebElement city;

    @FindBy(css = "input[placeholder='Enter State']")
    private WebElement state;

    @FindBy(css = "input[placeholder='Enter Pin Code']")
    private WebElement pinCode;

    @FindBy(xpath = "//div[@class='el-form-item is-required']/label[text()='Region']/following-sibling::div")
    private WebElement regionDropdown;

    @FindBy(xpath = "//span[text()='North']")
    private WebElement northRegion;

    @FindBy(xpath = "//span[@class='f-right']/button/following-sibling::button")
    private WebElement createDepotButton;

    @FindBy(xpath = "//div[@role='alert']//span")
    private WebElement depotSuccessMessage;

    @FindBy(xpath = "//div[@class='app-search-label']")
    private WebElement searchLabel;

    @FindBy(css = "button.btn.section_pbtn")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'el-col el-col-3')]//span[@class='link-action']")
    private WebElement editDepotButton;

    @FindBy(xpath = "//small[normalize-space()='Click here to add a gate']")
    private WebElement addGateLink;

    @FindBy(css = "input[placeholder='Enter Gate Name']")
    private WebElement gateName;

    @FindBy(css = "input[placeholder='Enter Reference Id']")
    private WebElement gateReferenceId;

    @FindBy(css = "input[placeholder='Enter Address']")
    private WebElement gateAddress;

    @FindBy(css = "input[placeholder='Enter City']")
    private WebElement gateCity;

    @FindBy(css = "input[placeholder='Enter State']")
    private WebElement gateState;

    @FindBy(css = "input[placeholder='Enter Pin Code']")
    private WebElement gatePinCode;

    @FindBy(xpath = "//input[@placeholder='Search user to add']")
    private WebElement searchUserInput;

    @FindBy(xpath = "(//div[contains(@class,'list-boxed') and contains(@class,'list-item')])[1]")
    private WebElement firstUserResult;

    @FindBy(xpath = "//main[@class='container-fluid main-content']//button/following-sibling::button")
    private WebElement saveGateButton;

    @FindBy(xpath = "//span[@class='el-breadcrumb__item']/span[text()='Depot']")
    private WebElement depotBreadcrumb;
  
    public void createDepot(String depotNameValue, String referenceId) {

        waitForWebElementToBeClickable(addDepotButton);
        addDepotButton.click();
        
        waitForWebElementToAppear(depotName);
        depotName.sendKeys(depotNameValue);
        
        Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String date = format.format(cal.getTime());
		System.out.println(date);		
		String[] splittedDate = date.split("/");
		System.out.println(splittedDate[1]);
        depotReferenceId.sendKeys(referenceId + splittedDate[1] );

        depotShortCode.sendKeys("SA129");
        gstin.sendKeys("09");
        address.sendKeys("Kelambakkam");
        city.sendKeys("Chennai");
        state.sendKeys("TamilNadu");
        pinCode.sendKeys("603103");

        regionDropdown.click();
        waitForWebElementToBeClickable(northRegion);
        northRegion.click();

        createDepotButton.click();
    }

    public String getDepotSuccessMessage() {
        waitForWebElementToAppear(depotSuccessMessage);
        return depotSuccessMessage.getText().trim();
    }

  
    public void searchAndOpenDepot(String depotReferenceIdValue) {

        waitForWebElementToBeClickable(depotBreadcrumb);
        depotBreadcrumb.click();

        waitForWebElementToBeClickable(searchLabel);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", searchLabel);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@placeholder='Enter Depot']")));

        WebElement searchInput =
                driver.findElement(By.xpath("//input[@placeholder='Enter Depot']"));

        searchInput.clear();
        searchInput.sendKeys(depotReferenceIdValue);

        waitForWebElementToBeClickable(searchButton);
        searchButton.click();

        waitForWebElementToBeClickable(editDepotButton);
        editDepotButton.click();
    }

 
    public void addGate(String gateNameValue,String gateRefIdValue,String addressValue, String cityValue,
                        String stateValue,String pinCodeValue, String userSearchText) {

        waitForWebElementToBeClickable(addGateLink);
        addGateLink.click();

        waitForWebElementToAppear(gateName);
        gateName.sendKeys(gateNameValue);

        gateReferenceId.sendKeys(gateRefIdValue);
        gateAddress.sendKeys(addressValue);
        gateCity.sendKeys(cityValue);
        gateState.sendKeys(stateValue);
        gatePinCode.sendKeys(pinCodeValue);

        regionDropdown.click();
        waitForWebElementToBeClickable(northRegion);
        northRegion.click();

        searchUserInput.clear();
        searchUserInput.sendKeys(userSearchText);

        waitForWebElementToAppear(firstUserResult);
        firstUserResult.click();

        saveGateButton.click();
    }


    public void navigateBackToDepot() {
        waitForWebElementToBeClickable(depotBreadcrumb);
        depotBreadcrumb.click();
    }
}
