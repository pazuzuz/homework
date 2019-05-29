package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page_factory.BasePage;

public class Navigation extends BasePage {
    public Navigation(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.LINK_TEXT, using = "groups")
    public WebElement groupsButton;

    @FindBy(how = How.LINK_TEXT, using = "home page")
    public WebElement returnToHomePageButton;


    public void openGroupPage() {
        click(groupsButton);
    }

    public void goBackToHomePage() {
        click(returnToHomePageButton);
    }
}
