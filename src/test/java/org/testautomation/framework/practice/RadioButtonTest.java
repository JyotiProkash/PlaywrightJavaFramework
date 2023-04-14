package org.testautomation.framework.practice;

import com.microsoft.playwright.Page;
import org.testautomation.framework.factory.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Properties;

public class RadioButtonTest {

    //url = https://demoqa.com/radio-button
    BrowserFactory bf;
    public Page page;
    protected Properties prop;
    private String yesRadioButton = "div.custom-control.custom-radio.custom-control-inline > label[for='yesRadio']";
    private String yesRadioButtonSelectedStatus = "//span[text()='Yes']";
    private String impressiveRadioButton = "div.custom-control.custom-radio.custom-control-inline > label[for='impressiveRadio']";
    private String impressiveRadioButtonSelectedStatus = "//span[text()='Impressive']";
    @BeforeTest
    public void setup() {
        bf = new BrowserFactory();
        prop = bf.initProp();
        page = bf.initBrowser(prop);
    }
    @Test(priority = 0)
    public void ToolsQARadioButtonPageUrlTest() {
        String actualText = page.url();
        Assert.assertEquals(actualText,prop.getProperty("url"));
    }

    @Test(priority = 1)
    public void ToolsQARadioButtonPageTitleTest() {
        String actualText = page.title();
        Assert.assertEquals(actualText,"DEMOQA");
    }

    //Radio button test using click() method
/*    @Test(priority = 2)
    public void RadioButtonTest() {
        page.click(yesRadioButton);
        page.isVisible(yesRadioButtonSelectedStatus);

        page.click(impressiveRadioButton);
        page.isVisible(impressiveRadioButtonSelectedStatus);
    }*/

    //Radio button test using check() method
/*    @Test(priority = 3)
    public void RadioButtonTest() {
        page.check(yesRadioButton);
        page.isVisible(yesRadioButtonSelectedStatus);

        page.check(impressiveRadioButton);
        page.isVisible(impressiveRadioButtonSelectedStatus);
    }*/

    //Radio button test using locator() and click() method
/*    @Test(priority = 4)
    public void RadioButtonTest() {
        page.locator(yesRadioButton).click();
        page.isVisible(yesRadioButtonSelectedStatus);

        page.locator(impressiveRadioButton).click();
        page.isVisible(impressiveRadioButtonSelectedStatus);
    }*/

    //Radio button test using locator() and check() method
/*    @Test(priority = 5)
    public void RadioButtonTest() {
        page.locator(yesRadioButton).check();
        page.isVisible(yesRadioButtonSelectedStatus);

        page.locator(impressiveRadioButton).check();
        page.isVisible(impressiveRadioButtonSelectedStatus);
    }*/

    //Radio button test selecting visible element OR visibility filter
    @Test(priority = 6)
    public void RadioButtonTest() {
        page.locator("text=Yes >> visible=true").check();
        page.isVisible(yesRadioButtonSelectedStatus);

        page.locator("text=Impressive >> visible=true").check();
        page.isVisible(impressiveRadioButtonSelectedStatus);
    }

}
