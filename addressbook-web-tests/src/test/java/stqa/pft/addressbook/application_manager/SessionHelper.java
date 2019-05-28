package stqa.pft.addressbook.application_manager;

import entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page_factory.PageGenerator;
import page_objects.LoginPage;

public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void loginAs(User user) {
        LoginPage loginPage = new PageGenerator(driver).getInstance(LoginPage.class);
        loginPage.open();
        loginPage.login(user);
//        driver.get("http://localhost/addressbook/");
//        type(By.name("user"), username);
//        type(By.name("pass"), password);
//        click(By.xpath("//input[@value='Login']"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }
}
