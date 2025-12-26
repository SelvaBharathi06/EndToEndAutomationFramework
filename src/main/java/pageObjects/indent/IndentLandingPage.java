package pageObjects.indent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.BasePage;

public class IndentLandingPage extends BasePage {

    public IndentLandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "li.app-side-menu")
    private WebElement menuButton;

    @FindBy(css = "li.sidemenu-slideout-menu-item a[href='/indents/v2/outbound']")
    private WebElement indentOutboundLink;

    @FindBy(xpath = "//button[contains(@class,'header_pbtn')]")
    private WebElement addIndentButton;

    public void openIndentLanding() {
        waitForWebElementToBeClickable(menuButton);
        menuButton.click();

        waitForWebElementToBeClickable(indentOutboundLink);
        indentOutboundLink.click();
    }
    
    public IndentSourcePage clickAddIndent() {
        waitForWebElementToBeClickable(addIndentButton);
        addIndentButton.click();
        return new IndentSourcePage(driver);
    }
}
