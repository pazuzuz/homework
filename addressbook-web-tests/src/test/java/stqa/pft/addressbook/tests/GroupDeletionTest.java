package stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase{

    @Test
    public void testGroupDeletion() {
        applicationManager.getNavigationHelper().goToGroupPage();
        if (! applicationManager.getGroupHelper().isThereAGroup()){
            applicationManager.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        applicationManager.getGroupHelper().selectGroup(before.size() - 1);
        applicationManager.getGroupHelper().deleteSelectedGroups();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}