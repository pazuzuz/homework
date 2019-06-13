package stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {
    @Test
    public void testGroupModification(){
        applicationManager.getNavigationHelper().goToGroupPage();
        if (! applicationManager.getGroupHelper().isThereAGroup()){
            applicationManager.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        int groupIndex = before.size() - 1;
        GroupData group = new GroupData(before.get(groupIndex).getId(), "test1", "test2", "test3");
        applicationManager.getGroupHelper().selectGroup(groupIndex);
        applicationManager.getGroupHelper().initGroupModification();
        applicationManager.getGroupHelper().fillGroupForm(group);
        applicationManager.getGroupHelper().submitGroupModification();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1);
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
