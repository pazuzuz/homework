package stqa.pft.addressbook.application_manager;


import model.User;
import org.openqa.selenium.WebDriver;
import page_factory.PageGenerator;
import page_objects.Users;

public class UserHelper extends HelperBase{
    private Users user = new PageGenerator(driver).getInstance(Users.class);

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void initAddNewUser() {
        user.createNew();
    }

    public void fillUserForm(User userData) {
        user.fillFormAs(userData);
    }

    public void submitNewUserForm() {
        user.submitForm();
    }

    public void initModifyUser() {
        user.initUpdate();
    }

    public void submitUpdateUserForm() {
        user.update();
    }

    public void selectUser() {
        user.selectUserFromList();
    }

    public void deleteSelectedUsers() {
        user.pressDeleteButton();
    }
}
