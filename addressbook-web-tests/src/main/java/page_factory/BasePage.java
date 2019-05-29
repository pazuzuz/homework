package page_factory;

import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasePage extends PageGenerator{

    public BasePage(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.LINK_TEXT, using = "Logout")
    public WebElement logoutButton;

    public <T> void click(T elementAttr){
        if(elementAttr.getClass().getName().contains("By")){
            driver.findElement((By) elementAttr).click();
        }else ((WebElement)elementAttr).click();
    }

    public <T> void clear(T elementAttr){
        if(elementAttr.getClass().getName().contains("By")){
            driver.findElement((By) elementAttr).click();
        }else ((WebElement)elementAttr).clear();
    }

    public <T> void writeText(T elementAttr, String text){
        click(elementAttr);
        clear(elementAttr);
        if(elementAttr.getClass().getName().contains("By")){
            driver.findElement((By) elementAttr).sendKeys(text);
        }else ((WebElement) elementAttr).sendKeys(text);
    }

    public void logout(){
        click(logoutButton);
    }
}
