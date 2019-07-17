package stqa.pft.mantis.application_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver driver;
    private String browser;
    private Properties properties;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private LocalMailHelper mailHelper;
    private RemoteMailHelper remote;
    private DbHelper db;
    private UserHelper user;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (driver != null){
            driver.quit();
        }
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp(){
        if (ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if(driver == null){
            switch (browser) {
                case BrowserType.FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case BrowserType.CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case BrowserType.IE:
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
            }
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public LocalMailHelper mail(){
        if (mailHelper == null){
            mailHelper =  new LocalMailHelper(this);
        }
        return mailHelper;
    }

    public RemoteMailHelper remote(){
        if (remote == null){
            remote = new RemoteMailHelper(this);
        }
        return remote;
    }

    public DbHelper db(){
        if (db == null){
            db = new DbHelper();
        }
        return db;
    }

    public UserHelper user(){
        if (user == null){
            user = new UserHelper(this);
        }
        return user;
    }
}
