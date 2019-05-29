package stqa.pft.addressbook.tests;

import model.LoginUser;
import model.User;
import org.testng.annotations.Test;

public class LoginUserModificationTest extends TestBase {
    @Test
    public void testUserModification(){
        applicationManager.getSessionHelper().loginAs(LoginUser.ADMIN);
        applicationManager.getUserHelper().initModifyUser();
        applicationManager.getUserHelper().fillUserForm(User.BENDER);
        applicationManager.getUserHelper().submitUpdateUserForm();
        applicationManager.getNavigationHelper().returnToHomePage();
    }
}
