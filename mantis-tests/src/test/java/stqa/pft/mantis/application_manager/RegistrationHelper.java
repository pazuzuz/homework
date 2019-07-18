package stqa.pft.mantis.application_manager;

import org.openqa.selenium.By;
import stqa.pft.mantis.model.User;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(User user) {
        driver.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), user.getUsername());
        type(By.name("email"), user.getEmail());
        click(By.cssSelector("input[value = 'Signup']"));
    }

    public void finish(String confirmationLink, User user) {
        driver.get(confirmationLink);
        type(By.name("password"), user.getPassword());
        type(By.name("password_confirm"), user.getPassword());
        click(By.cssSelector("button[type='submit']"));
    }
}
