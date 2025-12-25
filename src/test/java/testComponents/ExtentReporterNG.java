package testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {

        String path = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Pando Automation Results");
        reporter.config().setDocumentTitle("Automation Test Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Selva Bharathi");
        extent.setSystemInfo("Framework", "Selenium + TestNG + POM");

        return extent;
    }
}
