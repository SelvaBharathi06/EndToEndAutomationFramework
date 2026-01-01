package pageObjects.indent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.BasePage;

public class IndentAssignTruckPage extends BasePage {

    public IndentAssignTruckPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//button[@class='el-button el-button--default']")
    private WebElement filterButton;

    @FindBy(xpath = "//div[contains(@class,'cardlist-dropdown')]//input[contains(@placeholder,'Select')]")
    private WebElement filterSearchInput;

    //@FindBy(xpath = "//li[@class='el-select-dropdown__item']")
    //private List<WebElement> filterOption;

    @FindBy(xpath = "//button[@class='el-button btn section_pbtn el-button--default']")
    private WebElement applyFilterButton;

    @FindBy(xpath = "//span[@class='inputtextlabel']")
    private WebElement indentIdLabel;

    @FindBy(xpath = "//input[@placeholder='Enter Indent ID']")
    private WebElement indentIdInput;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@id='assign-truck-button']")
    private WebElement assignTruckButton;

    @FindBy(xpath = "//div[@class='el-form-item']//input[@placeholder='XX00']")
    private WebElement vehiclePrefix;

    @FindBy(xpath = "//div[@class='el-form-item']//input[@placeholder='0000']")
    private WebElement vehicleNumber;

    @FindBy(xpath = "//input[contains(@placeholder,'99XXXXXXXX')]")
    private WebElement driverMobile;

    @FindBy(xpath = "//input[@placeholder='Enter Driver Name']")
    private WebElement driverName;

    @FindBy(xpath = "//input[@placeholder='Enter Vehicle Length']")
    private WebElement vehicleLength;

    @FindBy(xpath = "//input[@placeholder='Enter Vehicle Width']")
    private WebElement vehicleWidth;

    @FindBy(xpath = "//input[@placeholder='Enter Vehicle height']")
    private WebElement vehicleHeight;

    @FindBy(xpath = "//div[@id='assign-truck-collapse']//button/following-sibling::button")
    private WebElement assignConfirmButton;

    @FindBy(xpath = "//div[@role='alert']//h3")
    private WebElement assignSuccessMessage;

    @FindBy(xpath = "//span[@id='truck-reported-text']")
    private WebElement truckReported;

    @FindBy(xpath = "//span[normalize-space()='Yes']")
    private WebElement truckReportedYes;

    @FindBy(xpath = "//div[@id='truckin-button']")
    private WebElement truckInButton;

    @FindBy(xpath = "//div[@class='action']//span//button[@type='button']")
    private WebElement truckInConfirmButton;

    @FindBy(xpath = "//a[@id='document-link']")
    private WebElement documentLink;

    @FindBy(xpath = "//span[normalize-space()='DOWNLOAD']")
    private WebElement downloadButton;

    public void filterIndent(String filterValue) throws InterruptedException {

        waitForWebElementToBeClickable(filterButton);
        filterButton.click();

        waitForWebElementToBeClickable(filterSearchInput);
        filterSearchInput.sendKeys(filterValue);
        
		/*for(int i=0; i<filterOption.size(); i++)
		{
			if(filterOption.get(i).getText().equalsIgnoreCase("jest87"))
			{
				filterOption.get(i).click();
			}
		}*/
        
        By optionLocator = By.xpath(
                "//div[contains(@class,'el-select-dropdown')]" +
                "//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='" + filterValue + "']"
            );
        
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
 
		Thread.sleep(1000);

        applyFilterButton.click();
    }

    public void searchIndentById(String indentId) throws InterruptedException {

        waitForWebElementToBeClickable(indentIdLabel);
        indentIdLabel.click();
        indentIdInput.sendKeys(indentId);
        Thread.sleep(2000);
        searchButton.click();
    }

    public void assignTruck(String prefix, String number, String mobile, String driver,
    		String length,String width,String height) {

        waitForWebElementToBeClickable(assignTruckButton);
        assignTruckButton.click();
        waitForWebElementToBeClickable(vehiclePrefix);
        vehiclePrefix.sendKeys(prefix);
        vehicleNumber.sendKeys(number);
        driverMobile.sendKeys(mobile);
        driverName.sendKeys(driver);

        vehicleLength.clear();
        vehicleLength.sendKeys(length);

        vehicleWidth.clear();
        vehicleWidth.sendKeys(width);

        vehicleHeight.clear();
        vehicleHeight.sendKeys(height);

        assignConfirmButton.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
        String asgndMsg =assignSuccessMessage.getText().trim();
		System.out.println(asgndMsg);
        
    }
    
    public String getIndentStatus(String indentId) {
        By status = By.id("indentv2-outbound-status-" + indentId);
        waitForPresenceOfElement(status);
        wait.until(ExpectedConditions.textToBe(status, "ASSIGNED"));
        String asgndStatus = driver.findElement(status).getText().trim();
		System.out.println(asgndStatus);
		return asgndStatus;
    }

    public void markTruckReported() {

        waitForWebElementToBeClickable(truckReported);
        truckReported.click();

        waitForWebElementToBeClickable(truckReportedYes);
        truckReportedYes.click();
    }

    public void markTruckIn(String indentID) {

        waitForWebElementToBeClickable(truckInButton);
        truckInButton.click();

        waitForWebElementToBeClickable(truckInConfirmButton);
        truckInConfirmButton.click();
        
        WebElement status1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("indentv2-outbound-status-"+indentID)));
		String arvdstatus = status1.getText().trim();
		System.out.println(arvdstatus);
    }
    
    

    public void downloadDocuments() {

        waitForWebElementToBeClickable(documentLink);
        documentLink.click();

        waitForWebElementToBeClickable(downloadButton);
        downloadButton.click();
    }
    
  

}
