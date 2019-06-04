package stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
}