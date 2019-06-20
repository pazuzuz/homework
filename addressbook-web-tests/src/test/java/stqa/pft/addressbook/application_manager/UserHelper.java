package stqa.pft.addressbook.application_manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;

import java.util.List;

public class UserHelper extends HelperBase{

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void initAddNewUser() {
        click(By.xpath("//a[@href='edit.php']"));
    }

    public void fillUserForm(UserData userData, boolean isUserCreation) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("address"), userData.getAddress());
        type(By.name("mobile"), userData.getMobile());
        type(By.name("email"), userData.getEmail());

        if (isUserCreation){
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitNewUserForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    private void initModifyUserById(int id) {
        driver.findElement(By.cssSelector("a[href='edit.php?id=" + id +"']")).click();
    }

    public void submitUpdateUserForm() {
        click(By.xpath("//input[@name='update']"));
    }

    private void selectUserById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(UserData userData, boolean isUserCreation) {
        initAddNewUser();
        fillUserForm(userData, isUserCreation);
        submitNewUserForm();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteSelectedUsers();
        if (isAlertPresent()){
            acceptAlert();
        }
    }

    public void modify(UserData user) {
        initModifyUserById(user.getId());
        fillUserForm(user, false);
        submitUpdateUserForm();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public Users all() {
        Users users = new Users();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element: elements ) {
            String firstname = element.findElements(By.tagName("td")).get(2).getText();
            String lastname = element.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            users.add(
                    new UserData()
                        .withId(id)
                        .withFirstName(firstname)
                        .withLastName(lastname))
            ;
        }
        return users;
    }
}
