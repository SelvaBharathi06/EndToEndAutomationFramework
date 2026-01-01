package pageObjects;

import java.io.IOException;
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
    
    @FindBy(css = "input[placeholder='Enter Pin Code']")
    private WebElement gatePinCode;

    @FindBy(css = "button[class='btn section_pbtn']")
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

    @FindBy(xpath = "//input[@placeholder='Enter Depot']")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@placeholder='Search user to add']")
    private WebElement searchUserInput;
    
    @FindBy(xpath = "//main[@class='container-fluid main-content']//button/following-sibling::button")
    private WebElement saveGateButton;

    @FindBy(xpath = "//span[@class='el-breadcrumb__item']/span[text()='Depot']")
    private WebElement depotBreadcrumb;
    
    @FindBy(xpath = "//button[normalize-space()='Upload']")
    private WebElement uploadBtn;
    
    @FindBy(xpath = "//table[contains(@class,'el-table__body')]//tbody/tr[1]")
    private WebElement firstRow;
    
    @FindBy(xpath = "//li[@class='el-select-dropdown__item']//span[contains(text(),'Depot Bulk Upload')]")
    private WebElement depotBulkUpload;
    
    @FindBy(xpath = "//button[@type='button']//span[contains(text(),'UPLOAD')]")
    private WebElement upload;
    
    @FindBy(xpath = "//button[@class='el-button ml-20 el-button--primary el-button--small']")
    private WebElement confirm;

    
    By firstResult = By.xpath("(//div[contains(@class,'list-boxed') and contains(@class,'list-item')])[1]");
  
    public void createDepot(String depotNameValue, String referenceId) throws InterruptedException {

        waitForWebElementToBeClickable(addDepotButton);
        addDepotButton.click();
        
        Thread.sleep(2000);
        waitForWebElementToAppear(depotName);
        depotName.sendKeys(depotNameValue);
            
        /*Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String date = format.format(cal.getTime());
		System.out.println(date);		
		String[] splittedDate = date.split("/");
		System.out.println(splittedDate[1]);*/
        depotReferenceId.sendKeys(referenceId);

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
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
    	String depotMsg =driver.findElement(By.xpath("//div[@role='alert']//span")).getText().trim();
		System.out.println(depotMsg);
        return depotMsg;
    }

  
    public void searchAndOpenDepot(String depotReferenceIdValue) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='app-search-label']")));
    	searchLabel.click();
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Depot']")));
        searchInput.clear();
        searchInput.sendKeys(depotReferenceIdValue);
        searchButton.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'el-col el-col-3')]//span[@class='link-action']")));
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
        WebElement searchUserInput = wait.until( ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search user to add']") ));
		searchUserInput.clear();
		searchUserInput.sendKeys("Selva");

		WebElement userResult = wait.until(ExpectedConditions.visibilityOfElementLocated(firstResult));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", userResult);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//main[@class='container-fluid main-content']//button/following-sibling::button"))).click();
				
    }


    public void navigateBackToDepot() {
    	
        waitForWebElementToBeClickable(depotBreadcrumb);
        depotBreadcrumb.click();
    }
    
    
    public void bulkDepotUpload() throws InterruptedException, IOException
    {
    	ExcelUtility.excel();  		
    	uploadBtn.click();	
		WebElement uploadTypeInput =firstRow.findElement(By.xpath(".//td[2]//input"));
		uploadTypeInput.click();
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(depotBulkUpload));
		option.click();				
		upload.click();
		Thread.sleep(1000);
		System.out.println("Starting Depot Bulk Upload...");
		Runtime.getRuntime().exec("C:\\Users\\shank\\Downloads\\SelvaDepotBulkUpload.exe");	
		System.out.println("Upload completed successfully");		
		//WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(confirm));
		//confirmButton.click();
		
    }
    
    
}
