package stqa.pft.addressbook.application_manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
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

    public void initModifyUser(int index) {
        driver.findElements(By.xpath("//img[@alt='EDIT']")).get(index).click();
//        click(By.xpath("//img[@alt='EDIT']"));
    }

    public void submitUpdateUserForm() {
        click(By.xpath("//input[@name='update']"));
    }

    public void selectUser(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//input[@value='DELETE']"));
    }

    public void createUser(UserData userData, boolean isUserCreation) {
        initAddNewUser();
        fillUserForm(userData, isUserCreation);
        submitNewUserForm();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<UserData> getUserList() {
        List<UserData> users = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element: elements ) {
            String firstname = element.findElements(By.tagName("td")).get(2).getText();
            String lastname = element.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            UserData user = new UserData(id, firstname, lastname, null, null, null, null);
            users.add(user);
        }
        return users;
    }
}
