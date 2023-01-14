package org.testautomation.framework.pages;

import com.microsoft.playwright.Page;

import java.util.List;

/*
Created by Jyoti
05/12/2022
 */
public class HomePage {

    private Page page;
    //Locators
/*    private String search ="input#Wikipedia1_wikipedia-search-input";
    private String searchIcon = "input.wikipedia-search-button";
    private String searchPageHeader ="div#Wikipedia1_wikipedia-search-results-header";
    private String searchResultsCountAndShowItems = "div#Wikipedia1_wikipedia-search-results div";
    */

    //Locators
    private  String myAccountLink = "#top > div.container > div.nav.float-end > ul > li:nth-child(2) > div > a > span";
    private String loginLink = "//a[text()='Login']";


    //Constructor method
    public HomePage(Page page) {
        this.page = page;
    }

    //Page Action Method
    public String getHomePageUrl() {

        return page.url();
    }

    public String getHomePageTitle() {

        return page.title();
    }
/*    public String doSearch(String searchKey) throws InterruptedException {
        page.fill(search,searchKey);
        page.click(searchIcon);
        return page.textContent(searchPageHeader);
    }*/

    public LoginPage navigateToLoginPage() {
        page.click(myAccountLink);
        page.isVisible(loginLink);
        page.click(loginLink);
        return new LoginPage(page);
    }
}
