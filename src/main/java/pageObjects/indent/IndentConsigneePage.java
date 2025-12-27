package pageObjects.indent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.BasePage;

public class IndentConsigneePage extends BasePage {

    public IndentConsigneePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div[@class='add-block']")
    private WebElement addDropOff;

    @FindBy(xpath = "//div[@id='tab-2']")
    private WebElement consigneeTab;

    @FindBy(xpath = "//input[@placeholder='Search by Consignee Name, City or ID']")
    private WebElement consigneeSearchInput;

    @FindBy(xpath = "//div[@id='pane-2']//tr//span[contains(@class,'el-checkbox__inner')]")
    private WebElement firstConsigneeCheckbox;

    @FindBy(xpath = "//div[@class='el-message-box__btns']/button/following-sibling::button")
    private WebElement confirmConsigneeBtn;

    @FindBy(xpath = "//div[@class='el-drawer__wrapper default-drawer']//button/following-sibling::button")
    private WebElement proceedToVehicleBtn;


    public IndentVehiclePage selectConsignee(String consigneeName) {

        waitForWebElementToBeClickable(addDropOff);
        addDropOff.click();

        waitForWebElementToBeClickable(consigneeTab);
        consigneeTab.click();

        waitForWebElementToBeClickable(consigneeSearchInput);
        consigneeSearchInput.sendKeys(consigneeName);

        waitForWebElementToBeClickable(firstConsigneeCheckbox);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", firstConsigneeCheckbox);

        waitForWebElementToBeClickable(confirmConsigneeBtn);
        confirmConsigneeBtn.click();

        waitForWebElementToBeClickable(proceedToVehicleBtn);
        proceedToVehicleBtn.click();

        return new IndentVehiclePage(driver);
    }
}
