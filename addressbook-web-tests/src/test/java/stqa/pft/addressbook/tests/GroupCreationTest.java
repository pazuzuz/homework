package stqa.pft.addressbook.tests;

import entity.User;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;

import java.util.Map;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        applicationManager.getSessionHelper().loginAs(User.ADMIN);
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().initGroupCreation();
        applicationManager.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        applicationManager.getGroupHelper().submitGroupCreation();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}