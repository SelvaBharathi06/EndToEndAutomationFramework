package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.BasePage;

public class MaterialsPage extends BasePage {

    public MaterialsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//button[contains(@class,'header_pbtn')]")
    private WebElement addMaterialButton;

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement materialName;

    @FindBy(xpath = "//input[@placeholder='Enter Code']")
    private WebElement materialCode;

    @FindBy(xpath = "//input[@placeholder='Enter Width']")
    private WebElement width;

    @FindBy(xpath = "//input[@placeholder='Enter Length']")
    private WebElement length;

    @FindBy(xpath = "//input[@placeholder='Enter Pce per box']")
    private WebElement piecesPerBox;

    @FindBy(xpath = "//input[@placeholder='Enter Height']")
    private WebElement height;

    @FindBy(xpath = "//input[@placeholder='Enter Weight']")
    private WebElement weight;

    @FindBy(xpath = "//input[@placeholder='Enter Division']")
    private WebElement division;

    @FindBy(xpath = "//button[@class='el-button el-button--primary el-button--small']")
    private WebElement createMaterialButton;

    @FindBy(xpath = "//div[@role='alert']//span")
    private WebElement successMessage;


    public void createMaterial(String name, String code) {

        waitForWebElementToBeClickable(addMaterialButton);
        addMaterialButton.click();

        waitForWebElementToAppear(materialName);
        materialName.sendKeys(name);
        materialCode.sendKeys(code);
        width.sendKeys("50");
        length.sendKeys("80");
        piecesPerBox.sendKeys("3");
        height.sendKeys("100");
        weight.sendKeys("50");
        division.sendKeys("Default");

        createMaterialButton.click();
    }

 
    public String getMaterialSuccessMessage() {
        waitForWebElementToAppear(successMessage);
        return successMessage.getText().trim();
    }
    
    
}
