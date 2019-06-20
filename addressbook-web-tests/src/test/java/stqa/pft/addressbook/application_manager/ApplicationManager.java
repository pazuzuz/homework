package stqa.pft.addressbook.application_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver driver;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private UserHelper userHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        switch (browser) {
            case BrowserType.FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
            case BrowserType.CHROME:
                WebDriverManager.chromedriver().setup();
                break;
            case BrowserType.IE:
                WebDriverManager.iedriver().setup();
                break;
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        userHelper = new UserHelper(driver);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        sessionHelper.logout();
        driver.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public UserHelper user() {
        return userHelper;
    }
}
