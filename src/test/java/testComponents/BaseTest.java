package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LoginPage;
import pageObjects.LandingPage;

public class BaseTest {

	protected WebDriver driver;
	protected Properties prop;
	protected LandingPage landingPage;


	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory",
			        System.getProperty("user.dir") + "\\" + prop.getProperty("download.path"));
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	@BeforeMethod
	public void launchApplication() throws IOException {

		driver = initializeDriver();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.goToLoginPage();
		loginPage.login(prop.getProperty("app.username"), prop.getProperty("app.password"));

		landingPage = new LandingPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public String takeScreenshot(String testName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/reports/screenshots/" + testName + ".png";

		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {

		File file = new File(filePath);		
		String jsonContent = FileUtils.readFileToString(file);	
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<HashMap<String, String>>> typeRef = new TypeReference<List<HashMap<String, String>>>() {};
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,typeRef);
		return data;
	}
}
