package page_objects;

import constant.Constant;
import entity.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page_factory.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.NAME, using = "user")
    public WebElement username;

    @FindBy(how = How.NAME, using = "pass")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//input[@value='Login']")
    public WebElement loginButton;

    public void login(User user){
        writeText(this.username, user.getUsername());
        writeText(this.password, user.getPassword());
        click(this.loginButton);
    }

    public void open(){
        driver.navigate().to(Constant.LOGIN_URL);
    }
}
