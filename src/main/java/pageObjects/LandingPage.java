package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.BasePage;
import pageObjects.indent.IndentLandingPage;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "li.app-side-menu")
    private WebElement menuIcon;

    @FindBy(css = "li.sidemenu-slideout-menu-item a[href='/mdmLanding']")
    private WebElement mdmLandingLink;

    @FindBy(xpath = "//h3[normalize-space()='Depot']")
    private WebElement depotMenu;

    @FindBy(xpath = "//h3[normalize-space()='Consignees']")
    private WebElement consigneeMenu;

    @FindBy(xpath = "//h3[normalize-space()='Transporter']")
    private WebElement transporterMenu;

    @FindBy(xpath = "//h3[normalize-space()='Materials']")
    private WebElement materialsMenu;

    @FindBy(xpath = "//h3[normalize-space()='Vehicles']")
    private WebElement vehiclesMenu;

    @FindBy(css = "li.sidemenu-slideout-menu-item a[href='/indents/v2/outbound']")
    private WebElement indentMenu;

  
    public void openMenu() {
        waitForWebElementToBeClickable(menuIcon);
        menuIcon.click();
    }

    
    public void openMDMLanding() {
        openMenu();
        waitForWebElementToBeClickable(mdmLandingLink);
        mdmLandingLink.click();
    }

    public DepotPage openDepotPage() {
        waitForWebElementToBeClickable(depotMenu);
        depotMenu.click();
        switchToNewWindow();
        return new DepotPage(driver);
    }

    public ConsigneePage openConsigneePage() {
        waitForWebElementToBeClickable(consigneeMenu);
        consigneeMenu.click();
        switchToNewWindow();
        return new ConsigneePage(driver);
    }

    public TransporterPage openTransporterPage() {
        waitForWebElementToBeClickable(transporterMenu);
        transporterMenu.click();
        return new TransporterPage(driver);
    }

    public MaterialsPage openMaterialsPage() {
        waitForWebElementToBeClickable(materialsMenu);
        materialsMenu.click();
        return new MaterialsPage(driver);
    }

    public VehiclePage openVehiclePage() {
        waitForWebElementToBeClickable(vehiclesMenu);
        vehiclesMenu.click();
        switchToNewWindow();
        return new VehiclePage(driver);
    }

    public IndentLandingPage openIndentLandingPage() {
        openMenu();
        waitForWebElementToBeClickable(indentMenu);
        indentMenu.click();
        return new IndentLandingPage(driver);
    }
}
