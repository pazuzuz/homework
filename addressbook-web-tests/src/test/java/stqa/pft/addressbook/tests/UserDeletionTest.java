package stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTest extends TestBase {
    @Test
    public void testUserDeletion(){
        applicationManager.getUserHelper().selectUser();
        applicationManager.getUserHelper().deleteSelectedUsers();
        if (applicationManager.getUserHelper().isAlertPresent()){
            applicationManager.getUserHelper().acceptAllert();
        }
    }
}
