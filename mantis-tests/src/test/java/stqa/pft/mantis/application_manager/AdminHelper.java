package stqa.pft.mantis.application_manager;

import org.openqa.selenium.By;
import stqa.pft.mantis.model.User;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }


    public void resetUserPassword(User user) {

        driver.findElement(By.cssSelector("a[href=/mantisbt-2.21.1/manage_overview_page.php']")).click();
    }
}

//*[@id="sidebar"]/ul/li[6]/a/span
