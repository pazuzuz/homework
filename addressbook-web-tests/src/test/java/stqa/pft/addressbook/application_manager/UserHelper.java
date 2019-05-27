package stqa.pft.addressbook.application_manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stqa.pft.addressbook.model.NewUserData;

public class UserHelper extends HelperBase{

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void initAddNewUser() {
        click(By.xpath("//a[@href='edit.php']"));
    }

    public void fillNewUserForm(NewUserData newUserData) {
        type(By.name("firstname"), newUserData.getFirstName());
        type(By.name("lastname"), newUserData.getLastName());
        type(By.name("address"), newUserData.getAddress());
        type(By.name("mobile"), newUserData.getMobile());
        type(By.name("email"), newUserData.getEmail());
    }

    public void submitNewUserForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initModifyUser() {
        click(By.xpath("//img[@alt='EDIT']"));
    }

    public void updateUserForm(NewUserData newUserData) {
        type(By.name("firstname"), newUserData.getFirstName());
        type(By.name("lastname"), newUserData.getLastName());
        type(By.name("address"), newUserData.getAddress());
        type(By.name("mobile"), newUserData.getMobile());
        type(By.name("email"), newUserData.getEmail());
    }

    public void submitUpdateUserForm() {
        click(By.xpath("//input[@name='update']"));
    }

    public void selectUser() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//input[@value='DELETE']"));
    }
}
