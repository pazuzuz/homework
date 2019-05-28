package page_factory;

import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage extends PageGenerator{

    public BasePage(WebDriver driver){
        super(driver);
    }

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

    public <T> void readText(T elementAttr){
        if(elementAttr.getClass().getName().contains("By")){
            driver.findElement((By) elementAttr).getText();
        }else ((WebElement) elementAttr).getText();
    }

    public void printDriverCapabilities(WebDriver driver){
        System.out.println(((HasCapabilities) this.driver).getCapabilities());
    }
}
