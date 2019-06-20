package stqa.pft.addressbook.application_manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && driver.findElement(By.tagName("h1")).getText().equals("groups")
                && isElementPresent(By.name("new"))){
            return;
        }
            click(By.linkText("groups"));
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }

    public void homePage(){
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.xpath("//a[@href=\"./\"]"));
    }
}
