package org.testautomation.framework.factory;

import com.microsoft.playwright.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

/*
Created by Jyoti
05/12/2022
 */
public class BrowserFactory {
//    Playwright playwright;
//    Browser browser;
//    BrowserContext browserContext;
//    Page page;
    public Properties prop;

    //ThreadLocal Implementation
    private static final ThreadLocal<Playwright> tLocalPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tLocalBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tLocalBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tLocalPage = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return tLocalPlaywright.get();
    }

    public static Browser getBrowser() {
        return tLocalBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tLocalBrowserContext.get();
    }

    public static Page getPage() {
        return tLocalPage.get();
    }


    public Page initBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim().toLowerCase();
        //playwright= Playwright.create();
        tLocalPlaywright.set(Playwright.create());
        if (browserName.equalsIgnoreCase("chromium")) {
            //browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            tLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(initProp().getProperty("headless")))));
        } else if (browserName.equalsIgnoreCase("chrome")) {
            //browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
            tLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(Boolean.parseBoolean(initProp().getProperty("headless")))));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //browser= playwright.firefox().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false));
            tLocalBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(Boolean.parseBoolean(initProp().getProperty("headless")))));
        } else if (browserName.equalsIgnoreCase("edge")) {
            //browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
            tLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(Boolean.parseBoolean(initProp().getProperty("headless")))));
        } else if (browserName.equalsIgnoreCase("safari")) {
            //browser= playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            tLocalBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(initProp().getProperty("headless")))));
        } else {
            System.out.println("Please enter browser name");
        }

        //browserContext=browser.newContext(new Browser.NewContextOptions().setViewportSize((int) maximizeBrowser().getWidth(),(int) maximizeBrowser().getHeight()));

        //Maximize Browser
        tLocalBrowserContext.set(getBrowser().newContext());
        //page = browserContext.newPage();
        tLocalPage.set(getBrowserContext().newPage());
        //page.navigate(prop.getProperty("url").trim());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
    }

    //To initialize the properties from config file
    public Properties initProp() {
        try {
            FileInputStream fs = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(fs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    //ScreenShot Capture Implementation
    public static String takeScreenShot() {
        String path = System.getProperty("user.dir") + "/screenshot/build/" + System.currentTimeMillis() + ".png";
        byte[] fileContent = getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
        return Base64.getEncoder().encodeToString(fileContent);
    }
}