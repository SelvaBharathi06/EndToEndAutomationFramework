package pageObjects.indent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	@FindBy(xpath = "//div[contains(@class,'el-select-dropdown') and contains(@style,'min-width')]//li")
	private WebElement vehicleTypeOption;

	@FindBy(xpath = "//input[@placeholder='INR']")
	private WebElement rateInput;

	//Select delivery type, vehicle type and rate
	public IndentTransporterPage selectVehicle(String vehicleType, String rate) {

		waitForWebElementToBeClickable(deliveryTypeInput);
		deliveryTypeInput.click();

		waitForWebElementToBeClickable(ftlOption);
		ftlOption.click();

		waitForWebElementToBeClickable(vehicleTypeInput);
		vehicleTypeInput.click();

		waitForWebElementToAppear(vehicleTypeOption);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",
				vehicleTypeOption);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", vehicleTypeOption);

		wait.until(driver -> vehicleTypeInput.getAttribute("value").contains(vehicleType));

		rateInput.clear();
		rateInput.sendKeys(rate);

		return new IndentTransporterPage(driver);
	}
}
