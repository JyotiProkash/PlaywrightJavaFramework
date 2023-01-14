package org.testautomation.framework.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    //Locators
    private String emailAddress = "#input-email";
    private String password = "#input-password";
    private  String loginButton = "button[type='submit']";

    //Constructor
    public LoginPage(Page page) {

        this.page = page;
    }
    //Page Action Method
    public String getLoginPageTitle() {

        return page.title();
    }

}
