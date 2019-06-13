package stqa.pft.addressbook.application_manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void goToGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && driver.findElement(By.tagName("h1")).getText().equals("GROUPS")
                && isElementPresent(By.name("new"))){
            return;
        }
            click(By.linkText("GROUPS"));
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }

    public void goToHomePage(){
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.xpath("//a[@href=\"./\"]"));
    }
}
