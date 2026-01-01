package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.BasePage;

public class TransporterPage extends BasePage {

	public TransporterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[contains(@class,'header_pbtn')]")
	private WebElement addTransporterButton;

	@FindBy(xpath = "//input[contains(@placeholder,'Transporter')]")
	private WebElement transporterName;

	@FindBy(xpath = "//input[contains(@placeholder,'Reference Id')]")
	private WebElement referenceId;

	@FindBy(xpath = "//input[contains(@placeholder,'GST Percentage')]")
	private WebElement gstPercentage;

	@FindBy(xpath = "//label[text()='GST Type']/following-sibling::div")
	private WebElement gstTypeDropdown;

	@FindBy(xpath = "//div[@x-placement='bottom-start']//li[@class='el-select-dropdown__item']")
	private WebElement gstTypeOption;

	@FindBy(xpath = "//label[normalize-space()='Status']/following-sibling::div")
	private WebElement statusDropdown;

	@FindBy(xpath = "(//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='Active'])[last()]")
	private WebElement activeStatusOption;

	@FindBy(xpath = "//h5[normalize-space()='Generate LR']/ancestor::div[contains(@class,'row')]//div[contains(@class,'el-switch')]")
	private WebElement generateLRSwitch;

	@FindBy(xpath = "//h5[normalize-space()='Auto Extend LR Series']/ancestor::div[contains(@class,'row')]//div[contains(@class,'el-switch')]")
	private WebElement autoExtendSwitch;

	@FindBy(xpath = "(//span[contains(text(),'Add')])[3]")
	private WebElement addManagerButton;

	@FindBy(css = "input[placeholder='Enter'][type='text']")
	private WebElement managerName;

	@FindBy(css = "input[placeholder='Enter'][type='number']")
	private WebElement managerMobile;

	@FindBy(xpath = "//input[contains(@placeholder,'Enter *')]")
	private WebElement managerEmail;

	@FindBy(xpath = "//button[@class='el-button el-button--primary el-button--small']")
	private WebElement createManagerButton;

	@FindBy(xpath = "//div[contains(@class,'left-section')]//li[@class='color-changes category']")
	private WebElement backToCategory;

	public void createTransporter(String name, String refId, String gstValue) {

		waitForWebElementToBeClickable(addTransporterButton);
		addTransporterButton.click();

		waitForWebElementToAppear(transporterName);
		transporterName.sendKeys(name);
		referenceId.sendKeys(refId);
		gstPercentage.sendKeys(gstValue);

		gstTypeDropdown.click();
		waitForWebElementToBeClickable(gstTypeOption);
		gstTypeOption.click();

		statusDropdown.click();
		waitForWebElementToBeClickable(activeStatusOption);
		activeStatusOption.click();

		//toggleSwitch(generateLRSwitch, true);
		//toggleSwitch(autoExtendSwitch, false);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'el-select-dropdown')]")));
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'el-drawer')]"))); 

		WebElement generateLRSwitch = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[normalize-space()='Generate LR']"
						+ "/ancestor::div[contains(@class,'row')]" + "//div[contains(@class,'el-switch')]")));
		boolean generateLRChecked = generateLRSwitch.getAttribute("class").contains("is-checked");
		if (!generateLRChecked) {
			 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", generateLRSwitch);
		}
		WebElement autoExtendSwitch = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[normalize-space()='Auto Extend LR Series']"
						+ "/ancestor::div[contains(@class,'row')]" + "//div[contains(@class,'el-switch')]")));
		boolean autoExtendChecked = autoExtendSwitch.getAttribute("class").contains("is-checked");
		if (autoExtendChecked) {
			 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", autoExtendSwitch);
		}
	}

	public void addManager(String name, String mobile, String email) {

		waitForWebElementToBeClickable(addManagerButton);
		addManagerButton.click();

		waitForWebElementToAppear(managerName);
		managerName.sendKeys(name);

		managerMobile.sendKeys(mobile);
		managerEmail.sendKeys(email);

		createManagerButton.click();
	}

	public void navigateBackToCategory() throws InterruptedException {
		waitForWebElementToBeClickable(backToCategory);
		backToCategory.click();
		Thread.sleep(1000);
	}

	/*Iprivate void toggleSwitch(WebElement toggle, boolean shouldBeOn) {
		boolean isOn = toggle.getAttribute("class").contains("is-checked");
		if (isOn != shouldBeOn) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
		}
	}*/
}
