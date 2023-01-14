package org.testautomation.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.testautomation.framework.factory.BrowserFactory.takeScreenShot;

public class ExtentReportListener implements ITestListener {
    private static final String OUTPUT_FOLDER = "./TestReports/";
    private static final String FILE_NAME = "TestExecutionReport.html";
    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;

    private static ExtentReports init() {
        // If directory exists
        Path path = Paths.get(OUTPUT_FOLDER);
        //If directory not exist
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // Fail to create directory
                e.printStackTrace();

            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        /*reporter.config().setReportName("Playwright Automation Test Results");
        reporter.config().setDocumentTitle("Selenium Automation");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setEncoding("utf-8");
        reporter.config().setReportName("Automation Execution Report");*/
        try {
            reporter.loadXMLConfig("src/test/resources/config/extent-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "Windows");
        extentReports.setSystemInfo("Platform", System.getProperty("os.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Browser Name", "Chrome");
        return extentReports;
    }

    //private Logger log = LoggerHelper.getLogger(TestListener.class);

    /**
     * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
     * filled with the references to class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     */
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);
        //log.info(result.getMethod().getMethodName() + " started.");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt; tag
     * and calling all their Configuration methods.
     *
     * @param context
     */
    public synchronized void onStart(ITestContext context) {
        extentReports = init();
        //extent.createTest(context.getName());
        extentReports.createTest(context.getCurrentXmlTest().getName());
        //log.info(context.getCurrentXmlTest().getName() + " started.");
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    public synchronized void onTestSuccess(ITestResult result) {
        String logText = "<b>" + result.getMethod().getMethodName() + "  successful.</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        test.get().log(Status.PASS, m);
        //test.get().pass("<b><font color=green>" + "Screenshot of Success" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        //log.info(result.getMethod().getMethodName() + " passed.");
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public synchronized void onTestFailure(ITestResult result) {

        String logText = "<b>" + result.getMethod().getMethodName() + "  failed.</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        test.get().log(Status.FAIL, m);
        //log.info(result.getMethod().getMethodName() + " failed.");

        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        test.get().fail("<details><summary><b><font color=red>" +
                "Exception, click to see details:" + "</font></b></summary>" +
                exceptionMessage.replaceAll(",", "<br>") + "</details>\n");
        test.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    /**
     * Invoked each time a method fails but has been annotated with successPercentage and this failure
     * still keeps it within the success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result) {
        String logText = "<b>" + result.getMethod().getMethodName() + " skipped.</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        test.get().log(Status.SKIP, m);
        //log.info(result.getMethod().getMethodName() + " skipped.");
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        test.get().skip("<details><summary><b><font color=yellow>" +
                "Exception, click to see details:" + "</font></b></summary>" +
                exceptionMessage.replaceAll(",", "<br>") + "</details>\n");
        test.get().skip("<b><font color=yellow>" + "screenshot of skipping" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String logText = "<b>onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName() + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        test.get().fail(m);
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    /**
     * Invoked each time a test fails due to a timeout.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    public void onTestFailedWithTimeout(ITestResult result) {
        String logText = "<b>Failed " + result.getMethod().getMethodName() + " for timeout.</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        test.get().fail(m);
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have run
     * and all their Configuration methods have been called.
     *
     * @param context
     */
    public synchronized void onFinish(ITestContext context) {
        if (test != null)
            extent.flush();
        test.remove();
        //log.info(context.getName() + " finished.");
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
