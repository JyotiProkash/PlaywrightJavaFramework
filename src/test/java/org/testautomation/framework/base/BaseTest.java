package org.testautomation.framework.base;

import com.microsoft.playwright.Page;
import org.testautomation.framework.factory.BrowserFactory;
import org.testautomation.framework.pages.HomePage;
import org.testautomation.framework.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {

    BrowserFactory bf;
    public Page page;
    protected Properties prop;
    protected HomePage homePage;
    protected LoginPage loginPage;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName) {
        bf = new BrowserFactory();
        prop = bf.initProp();
        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }
        page = bf.initBrowser(prop);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }
}
