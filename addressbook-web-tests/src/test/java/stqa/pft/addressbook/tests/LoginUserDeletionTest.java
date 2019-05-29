package stqa.pft.addressbook.tests;

import model.LoginUser;
import org.testng.annotations.Test;

public class LoginUserDeletionTest extends TestBase {
    @Test
    public void testUserDeletion(){
        applicationManager.getSessionHelper().loginAs(LoginUser.ADMIN);
        applicationManager.getUserHelper().selectUser();
        applicationManager.getUserHelper().deleteSelectedUsers();
        if (applicationManager.getUserHelper().isAlertPresent()){
            applicationManager.getUserHelper().acceptAlert();
        }
    }
}
