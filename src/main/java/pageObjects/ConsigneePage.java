package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.BasePage;

public class ConsigneePage extends BasePage {

	public ConsigneePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[contains(@class,'header_pbtn')]")
	private WebElement addConsigneeButton;

	@FindBy(xpath = "//input[@placeholder='Enter Consignee Name']")
	private WebElement consigneeName;

	@FindBy(xpath = "//input[@placeholder='Enter Reference Id']")
	private WebElement referenceId;

	@FindBy(xpath = "//input[@placeholder='Enter State']")
	private WebElement state;

	@FindBy(xpath = "//input[@placeholder='Enter Region']")
	private WebElement region;

	@FindBy(xpath = "//input[@placeholder='Enter City']")
	private WebElement city;

	@FindBy(xpath = "//input[@placeholder='Enter Postal code']")
	private WebElement postalCode;

	@FindBy(xpath = "//input[@placeholder='Enter Representative Mobile']")
	private WebElement representativeMobile;

	@FindBy(css = "div.f-right")
	private WebElement createButton;

	@FindBy(xpath = "//div[@role='alert']//span")
	private WebElement successMessage;

	@FindBy(xpath = "//div[contains(@class,'left-section')]//li[@class='color-changes category']")
	private WebElement backToCategory;

	public void createConsignee(String name, String refId, String stateValue, String regionValue, String cityValue,
			String postal, String mobile) {

		waitForWebElementToBeClickable(addConsigneeButton);
		addConsigneeButton.click();

		waitForWebElementToAppear(consigneeName);
		consigneeName.sendKeys(name);

		referenceId.sendKeys(refId);
		state.sendKeys(stateValue);
		region.sendKeys(regionValue);
		city.sendKeys(cityValue);
		postalCode.sendKeys(postal);
		representativeMobile.sendKeys(mobile);

		createButton.click();
	}

	public String getConsigneeSuccessMessage() {
		waitForWebElementToAppear(successMessage);
		return successMessage.getText().trim();
	}

	public void navigateBackToCategory() {
		waitForWebElementToBeClickable(backToCategory);
		backToCategory.click();
	}
}
