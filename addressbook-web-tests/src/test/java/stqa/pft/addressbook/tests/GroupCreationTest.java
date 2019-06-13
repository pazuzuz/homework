package stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        applicationManager.getNavigationHelper().goToGroupPage();
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test2", null, null);
        applicationManager.getGroupHelper().createGroup(group);
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        group.setId(after.stream().max(byId).get().getId());
        before.add(group);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}