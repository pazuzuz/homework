package stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTest extends TestBase {
    @Test
    public void testUserModification(){
        if (! applicationManager.getUserHelper().isThereAUser()) {
            applicationManager.getUserHelper().createUser(
                 new UserData(
                         "Morbo",
                         "Annulyator",
                         "New New York City, 12313, Westend",
                         "80993452312",
                         "morbo_annulyator@gmail.com",
                         "test1"), true);
        }
        applicationManager.getNavigationHelper().returnToHomePage();
        List<UserData> before = applicationManager.getUserHelper().getUserList();
        int userIndex = before.size() - 1;
        UserData user =
                new UserData(
                          before.get(userIndex).getId(),
                         "Pazuzu",
                         "Annulyator",
                         "New New York City, 12313, Westend",
                         "80123432332",
                         "pazuzu_annulyator@gmail.com",
                         null);
        applicationManager.getUserHelper().initModifyUser(userIndex);
        applicationManager.getUserHelper().fillUserForm(user, false);
        applicationManager.getUserHelper().submitUpdateUserForm();
        applicationManager.getNavigationHelper().returnToHomePage();
        List<UserData> after = applicationManager.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(userIndex);
        before.add(user);
        Comparator<? super UserData> byId = Comparator.comparingInt(UserData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
