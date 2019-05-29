package stqa.pft.addressbook.tests;

import model.Group;
import model.LoginUser;
import org.testng.annotations.Test;

public class GroupModificationTest extends TestBase {
    @Test
    public void testGroupModification(){
        applicationManager.getSessionHelper().loginAs(LoginUser.ADMIN);
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().initGroupModification();
        applicationManager.getGroupHelper().fillGroupForm(Group.TEST_2);
        applicationManager.getGroupHelper().submitGroupModification();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
