package page_objects;

import constant.Constant;
import model.LoginUser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page_factory.BasePage;

public class Login extends BasePage {

    public Login(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.NAME, using = "user")
    public WebElement username;

    @FindBy(how = How.NAME, using = "pass")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//input[@value='Login']")
    public WebElement loginButton;

    public void login(LoginUser loginUser){
        writeText(this.username, loginUser.getUsername());
        writeText(this.password, loginUser.getPassword());
        click(this.loginButton);
    }

    public void open(){
        driver.navigate().to(Constant.LOGIN_URL);
    }
}
