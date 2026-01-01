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

    @FindBy(xpath = "//span[text()='Transporter']")
    private WebElement transporterMenu;

    @FindBy(xpath = "//span[text()='Materials']")
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
        switchToChildWindow();
        return new DepotPage(driver);
    }

    public ConsigneePage openConsigneePage() {
        waitForWebElementToBeClickable(consigneeMenu);
        consigneeMenu.click();
        switchToChildWindow();
        return new ConsigneePage(driver);
    }

    public TransporterPage openTransporterPage() throws InterruptedException {
        waitForWebElementToBeClickable(transporterMenu);
        transporterMenu.click();
        Thread.sleep(2000);
        return new TransporterPage(driver);
    }

    public MaterialsPage openMaterialsPage() throws InterruptedException {
    	
        waitForWebElementToBeClickable(materialsMenu);
        materialsMenu.click();
        Thread.sleep(1000);
        return new MaterialsPage(driver);
    }

    public VehiclePage openVehiclePage() {
        waitForWebElementToBeClickable(vehiclesMenu);
        vehiclesMenu.click();
        switchToChildWindow();
        return new VehiclePage(driver);
    }

    public IndentLandingPage openIndentLandingPage() {
        openMenu();
        waitForWebElementToBeClickable(indentMenu);
        indentMenu.click();
        return new IndentLandingPage(driver);
    }
}
