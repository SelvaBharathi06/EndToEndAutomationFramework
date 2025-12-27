package abstractComponents;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected String parentWindow;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li.app-side-menu")
	protected WebElement sideMenuIcon;

	@FindBy(xpath = "//h3[contains(@class,'justify-content-between')]")
	private List<WebElement> sideMenuItems;

	public void waitForWebElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForWebElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void switchToNewWindow() {
		parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iterator = windows.iterator();
		iterator.next(); // parent
		driver.switchTo().window(iterator.next()); // child
	}

	public void closeAndReturnToParent() {
		
	    driver.close();
	    driver.switchTo().window(parentWindow);	      
	}


}
