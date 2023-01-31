package org.testautomation.framework.practice;

import com.microsoft.playwright.Page;
import org.testautomation.framework.factory.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Properties;

public class TextFieldTest {

    //url = https://demoqa.com/text-box
    BrowserFactory bf;
    public Page page;
    protected Properties prop;
    private String fullName = "input#userName";
    private String emailAddress = "input#userEmail";
    private String currentAddress = "textarea#currentAddress";
    private String permanentAddress = "textarea#permanentAddress";

    @BeforeTest
    public void setup() {
        bf = new BrowserFactory();
        prop = bf.initProp();
        page = bf.initBrowser(prop);
    }

    @Test(priority = 0)
    public void TOOLSQATextBoxPageUrlTest() {
        String actualText = page.url();
        Assert.assertEquals(actualText, prop.getProperty("url"));
    }

    @Test(priority = 1)
    public void TOOLSQATextBoxPageTitleTest() {
        String actualText = page.title();
        Assert.assertEquals(actualText, "DEMOQA");
    }

    @Test(priority = 2)
    public void TOOLSQATextBoxInputValueTest() {
/*        Locator nameField = page.locator(fullName);
        nameField.fill("Test");

        Locator emailField = page.locator(emailAddress);
        emailField.fill("Test");

        Locator currentAddressField = page.locator(currentAddress);
        currentAddressField.fill("Test");

        Locator permanentAddressField = page.locator(permanentAddress);
        permanentAddressField.fill("Test");*/

    }
}
