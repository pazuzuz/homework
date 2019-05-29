package stqa.pft.addressbook.tests;

import model.LoginUser;
import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase{

    @Test
    public void testGroupDeletion() {
        applicationManager.getSessionHelper().loginAs(LoginUser.ADMIN);
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().deleteSelectedGroups();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}