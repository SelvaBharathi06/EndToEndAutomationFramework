package pageObjects.indent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.BasePage;

public class IndentVehiclePage extends BasePage {

	public IndentVehiclePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//label[normalize-space()='Delivery Type']/following-sibling::div//input")
	private WebElement deliveryTypeInput;

	@FindBy(xpath = "//div[@x-placement='bottom-start']//li[normalize-space()='FTL']")
	private WebElement ftlOption;

	@FindBy(xpath = "//label[normalize-space()='Vehicle type']/following-sibling::div//input")
	private WebElement vehicleTypeInput;

	//@FindBy(xpath = "//div[contains(@class,'el-select-dropdown') and contains(@style,'min-width')]//li")
	//private WebElement vehicleTypeOption;

	@FindBy(xpath = "//input[@placeholder='INR']")
	private WebElement rateInput;
	
	By vehicleTypeOption = By.xpath("//div[contains(@class,'el-select-dropdown') "
			+ "and contains(@style,'min-width')]" +"//li[.//span[normalize-space()='Sat12']]");

	public IndentTransporterPage selectVehicle(String vehicleType) {

		waitForWebElementToBeClickable(deliveryTypeInput);
		deliveryTypeInput.click();

		waitForWebElementToBeClickable(ftlOption);
		ftlOption.click();

		waitForWebElementToBeClickable(vehicleTypeInput);
		vehicleTypeInput.click();

		waitForPresenceOfElement(vehicleTypeOption);
		
		WebElement vehicleOptionLi = wait.until(ExpectedConditions.presenceOfElementLocated( By.xpath("//div[contains(@class,'el-select-dropdown') "
				+ "and contains(@style,'min-width')]" +"//li[.//span[normalize-space()='Sat12']]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", vehicleOptionLi);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", vehicleOptionLi);
		wait.until(ExpectedConditions.attributeContains( vehicleTypeInput,"value", "Sat12"));		
		
		rateInput.clear();
		rateInput.sendKeys("10");

		return new IndentTransporterPage(driver);
	}
}
