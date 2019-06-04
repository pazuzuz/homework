package stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase{

    @Test
    public void testGroupDeletion() {
        applicationManager.getNavigationHelper().goToGroupPage();
        if (! applicationManager.getGroupHelper().isThereAGroup()){
            applicationManager.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().deleteSelectedGroups();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}