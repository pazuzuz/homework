package stqa.pft.mantis.application_manager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    private ApplicationManager app;
    private WebDriver driver;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        this.driver = app.getDriver();
    }


    public void start(String username, String email) {
        driver.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
