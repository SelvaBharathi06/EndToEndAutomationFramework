package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.BasePage;

public class VehiclePage extends BasePage {

    public VehiclePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@class,'header_pbtn')]")
    private WebElement addVehicleButton;

    @FindBy(xpath = "//input[@placeholder='Enter Vehicle Name here']")
    private WebElement vehicleName;

    @FindBy(xpath = "//input[@placeholder='Enter CFT here']")
    private WebElement cft;

    @FindBy(xpath = "//input[@placeholder='Enter KG here']")
    private WebElement kg;

    @FindBy(xpath = "//div[@class='el-form-item is-required']/label[text()='Vehicle Type']/following-sibling::div")
    private WebElement vehicleTypeDropdown;

    @FindBy(xpath = "//div[@x-placement='bottom-start']//li[@class='el-select-dropdown__item']")
    private WebElement vehicleTypeOption;

    @FindBy(xpath = "//div[@class='el-form-item is-required']/label[text()='Fuel Type']/following-sibling::div")
    private WebElement fuelTypeDropdown;

    @FindBy(xpath = "(//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='Diesel'])[last()]")
    private WebElement dieselFuelOption;

    @FindBy(xpath = "//div[@class='header-action']//button[@class='el-button el-button--primary el-button--mini']")
    private WebElement createVehicleButton;

    @FindBy(xpath = "//div[@role='alert']//span")
    private WebElement successMessage;

    public void createVehicle(String name,
                              String cftValue,
                              String kgValue) {

        waitForWebElementToBeClickable(addVehicleButton);
        addVehicleButton.click();

        waitForWebElementToAppear(vehicleName);
        vehicleName.sendKeys(name);

        cft.sendKeys(cftValue);
        kg.sendKeys(kgValue);

        vehicleTypeDropdown.click();
        waitForWebElementToBeClickable(vehicleTypeOption);
        vehicleTypeOption.click();

        fuelTypeDropdown.click();
        waitForWebElementToBeClickable(dieselFuelOption);
        dieselFuelOption.click();

        createVehicleButton.click();
    }


    public String getVehicleSuccessMessage() {
        waitForWebElementToAppear(successMessage);
        return successMessage.getText().trim();
    }
}