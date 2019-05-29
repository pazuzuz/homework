package page_objects;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page_factory.BasePage;

public class Users extends BasePage {
    public Users(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//a[@href='edit.php']")
    public WebElement addNewButton;

    @FindBy(how = How.NAME, using = "firstname")
    public WebElement firstName;

    @FindBy(how = How.NAME, using = "lastname")
    public WebElement lastname;

    @FindBy(how = How.NAME, using = "address")
    public WebElement address;

    @FindBy(how = How.NAME, using = "mobile")
    public WebElement mobile;

    @FindBy(how = How.NAME, using = "email")
    public WebElement email;

    @FindBy(how = How.XPATH, using = "(//input[@name='submit'])[2]")
    public WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//img[@alt='Edit']")
    public WebElement initUpdateButton;

    @FindBy(how = How.XPATH, using = "//input[@name='update']")
    public WebElement updateButton;

    @FindBy(how = How.NAME, using = "selected[]")
    public WebElement selectUser;

    @FindBy(how = How.XPATH, using = "//input[@value='Delete']")
    public WebElement deleteButton;

    public void createNew() {
        click(addNewButton);
    }

    public void fillFormAs(User user) {
        writeText(firstName, user.getFirstName());
        writeText(lastname, user.getLastName());
        writeText(address, user.getAddress());
        writeText(mobile, user.getMobile());
        writeText(email, user.getEmail());
    }

    public void submitForm() {
        click(submitButton);
    }

    public void initUpdate() {
        click(initUpdateButton);
    }

    public void update() {
        click(updateButton);
    }

    public void selectUserFromList() {
        click(selectUser);
    }

    public void pressDeleteButton() {
        click(deleteButton);
    }
}
