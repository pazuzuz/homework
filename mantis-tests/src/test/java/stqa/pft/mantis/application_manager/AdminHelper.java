package stqa.pft.mantis.application_manager;

import org.openqa.selenium.By;
import stqa.pft.mantis.model.User;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void signIn() {
        User user = new User().withUsername("administrator").withPassword("root");
        driver.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.id("username"), user.getUsername());
        click(By.cssSelector("input[value = 'Login']"));
        type(By.id("password"), user.getPassword());
        click(By.cssSelector("input[value = 'Login']"));
    }

    public void resetUserPassword(User user) {
        driver.findElement(By.cssSelector("a[href='/mantisbt-2.21.1/manage_overview_page.php']")).click();
        driver.findElement(By.cssSelector("a[href='/mantisbt-2.21.1/manage_user_page.php']")).click();
        driver.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + user.getId() + "']")).click();
        driver.findElement(By.cssSelector("input[value='Reset Password']")).click();
    }
}
