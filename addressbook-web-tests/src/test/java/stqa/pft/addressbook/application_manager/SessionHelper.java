package stqa.pft.addressbook.application_manager;

import model.LoginUser;
import org.openqa.selenium.WebDriver;
import page_factory.BasePage;
import page_factory.PageGenerator;
import page_objects.Login;

public class SessionHelper extends HelperBase{
    private Login login = new PageGenerator(driver).getInstance(Login.class);
    private BasePage base = new PageGenerator(driver).getInstance(BasePage.class);

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void loginAs(LoginUser loginUser) {
        login.open();
        login.login(loginUser);
    }

    public void logout() {
        base.logout();
    }
}