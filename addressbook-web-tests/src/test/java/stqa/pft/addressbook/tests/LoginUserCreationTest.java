package stqa.pft.addressbook.tests;

import model.LoginUser;
import model.User;
import org.testng.annotations.Test;

public class LoginUserCreationTest extends TestBase{

    @Test
    public void testUserCreation() {
        applicationManager.getSessionHelper().loginAs(LoginUser.ADMIN);
        applicationManager.getUserHelper().initAddNewUser();
        applicationManager.getUserHelper().fillUserForm(User.MORBO);
        applicationManager.getUserHelper().submitNewUserForm();
        applicationManager.getNavigationHelper().returnToHomePage();
    }
}