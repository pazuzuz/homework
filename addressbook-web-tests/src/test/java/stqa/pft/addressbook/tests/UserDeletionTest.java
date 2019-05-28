package stqa.pft.addressbook.tests;

import entity.User;
import org.testng.annotations.Test;

public class UserDeletionTest extends TestBase {
    @Test
    public void testUserDeletion(){
        applicationManager.getSessionHelper().loginAs(User.ADMIN);
        applicationManager.getUserHelper().selectUser();
        applicationManager.getUserHelper().deleteSelectedUsers();
        if (applicationManager.getUserHelper().isAlertPresent()){
            applicationManager.getUserHelper().acceptAlert();
        }
    }
}
