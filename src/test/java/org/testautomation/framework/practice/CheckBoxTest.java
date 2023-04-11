package org.testautomation.framework.practice;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testautomation.framework.factory.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

public class CheckBoxTest {

    //url = https://demoqa.com/checkbox
    BrowserFactory bf;
    public Page page;
    protected Properties prop;
    private String checkBoxUncheck = "svg.rct-icon.rct-icon-uncheck";
    private String checkBoxChecked = "svg.rct-icon.rct-icon-check";
    @BeforeTest
    public void setup() {
        bf = new BrowserFactory();
        prop = bf.initProp();
        page = bf.initBrowser(prop);
    }
    @Test(priority = 0)
    public void ToolsQACheckBoxPageUrlTest() {
        String actualText = page.url();
        Assert.assertEquals(actualText,prop.getProperty("url"));
    }

    @Test(priority = 1)
    public void ToolsQACheckBoxPageTitleTest() {
        String actualText = page.title();
        Assert.assertEquals(actualText,"DEMOQA");
    }

    //CheckBox select/unselect using click() method
/*    @Test(priority = 2)
    public void checkBoxCheckTest() {
        page.click(checkBoxUncheck);
        page.isVisible(checkBoxChecked);
    }*/

/*    @Test(priority = 3)
    public void checkBoxUncheckTest() {
        page.click(checkBoxChecked);
        page.isVisible(checkBoxUncheck);
    }*/

    //CheckBox select using check() method
/*    @Test(priority = 4)
    public void checkBoxCheckTest() {
        page.check(checkBoxUncheck);
        page.isVisible(checkBoxChecked);
    }*/

    //CheckBox unselect using uncheck() method
/*    @Test(priority = 5)
    public void checkBoxUncheckTest() {
        page.uncheck(checkBoxChecked);
        page.isVisible(checkBoxUncheck);
    }*/
}
