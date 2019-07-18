package stqa.pft.mantis.application_manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{


    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

//    public void groupPage() {
//        if (isElementPresent(By.tagName("h1"))
//                && driver.findElement(By.tagName("h1")).getText().equals("groups")
//                && isElementPresent(By.name("new"))){
//            return;
//        }
//            click(By.linkText("groups"));
//    }
//
//    public void homePage(){
//        if(isElementPresent(By.id("maintable"))){
//            return;
//        }
//        click(By.xpath("//a[@href=\"./\"]"));
//    }

    public void loginPage(){
        if (isElementPresent(By.xpath("//*[@id='login-box']//h4").))
    }
}
//*[@id="login-box"]/div/div[1]/h4
//*[@id="login-box"]//h4/text()