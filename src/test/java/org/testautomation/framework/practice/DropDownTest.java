package org.testautomation.framework.practice;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import org.testautomation.framework.base.BaseTest;
import org.testautomation.framework.constants.AppConstants;
import org.testautomation.framework.factory.BrowserFactory;
import org.testautomation.framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

public class DropDownTest{

    //url = https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login
    BrowserFactory bf;
    public Page page;
    protected Properties prop;
    private String customerLoginButton = "//button[text()='Customer Login']";
    private String selectNameDropdown = "select#userSelect";
    private String loginButton = "//button[text()='Login']";
    @BeforeTest
    public void setup() {
        bf = new BrowserFactory();
        prop = bf.initProp();
        page = bf.initBrowser(prop);
    }
    @Test(priority = 0)
    public void XYZBankHomePageUrlTest() {
        String actualText = page.url();
        Assert.assertEquals(actualText,prop.getProperty("url"));
    }

    @Test(priority = 1)
    public void XYZBankHomePageTitleTest() {
        String actualText = page.title();
        Assert.assertEquals(actualText,"XYZ Bank");
    }

    @Test(priority = 2)
    public void NavigateToCustomerLoginPageTest() {
        page.click(customerLoginButton);
        page.isVisible(selectNameDropdown);
    }
    @Test(priority = 3)
    public void NameSelectionFromDropDownAndLoginTest() {
        //Select by value
/*        Locator dropdownValue = page.locator(selectNameDropdown);
        //dropdownValue.selectOption("3");
        dropdownValue.selectOption(new SelectOption().setValue("3"));*/

        //Select by visible text
/*        Locator dropdownValue = page.locator(selectNameDropdown);
        dropdownValue.selectOption(new SelectOption().setLabel("Harry Potter"));*/

        //Select by index
/*        Locator dropdownValue = page.locator(selectNameDropdown);
        dropdownValue.selectOption(new SelectOption().setIndex(3));*/

/*        //Multiple option selection using element handle
        Locator multipleValue = page.locator(selectNameDropdown);
        multipleValue.selectOption(new String[]{"3","5","8"});*/

        //Select value from dropdown using index by counting length and find length of options
/*        Locator lastValue = page.locator(selectNameDropdown);
        Locator options = lastValue.locator("option");
        int count = options.count();
        lastValue.selectOption(new SelectOption().setIndex(count-1));*/

        //Select value from dropdown using all options inner text count and print all options
/*        Locator selectValue = page.locator(selectNameDropdown);
        Locator options = selectValue.locator("option");
        List<String> allInnerTexts = options.allInnerTexts();
        allInnerTexts.forEach(i -> System.out.println(i));
        allInnerTexts.size();*/

    }
}
