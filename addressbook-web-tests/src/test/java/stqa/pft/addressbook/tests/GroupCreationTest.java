package stqa.pft.addressbook.tests;

import model.Group;
import model.LoginUser;
import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        applicationManager.getSessionHelper().loginAs(LoginUser.ADMIN);
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().initGroupCreation();
        applicationManager.getGroupHelper().fillGroupForm(Group.TEST_1);
        applicationManager.getGroupHelper().submitGroupCreation();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}