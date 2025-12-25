package pageObjects.indent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.BasePage;

public class IndentSourcePage extends BasePage {

    public IndentSourcePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//span[normalize-space()='Add Source location']")
    private WebElement addSourceLocationBtn;

    @FindBy(xpath = "//input[@placeholder='Search by DEPOT Name, city or ID']")
    private WebElement sourceSearchInput;

    @FindBy(xpath = "//div[contains(@class,'el-table__body-wrapper')]//tr[contains(@class,'el-table__row')]")
    private WebElement sourceTableRow;

    @FindBy(xpath = "(//div[contains(@class,'el-table__body-wrapper')]//label[contains(@class,'el-radio')])[1]")
    private WebElement firstSourceRadio;

    @FindBy(xpath = "//div[@aria-labelledby='el-drawer__title']//button/following-sibling::button")
    private WebElement confirmSourceButton;


    //Select source depot and proceed to Consignee step
    public IndentConsigneePage selectSourceDepot(String depotName) {

        waitForWebElementToBeClickable(addSourceLocationBtn);
        addSourceLocationBtn.click();

        waitForWebElementToBeClickable(sourceSearchInput);
        sourceSearchInput.sendKeys(depotName);

        // Wait until table loads at least one row
        wait.until(ExpectedConditions.visibilityOf(sourceTableRow));

        // Click first radio safely (same as Demo JS click)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", firstSourceRadio);

        waitForWebElementToBeClickable(confirmSourceButton);
        confirmSourceButton.click();

        return new IndentConsigneePage(driver);
    }
}
