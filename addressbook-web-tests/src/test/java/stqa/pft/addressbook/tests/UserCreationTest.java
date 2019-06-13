package stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTest extends TestBase{

    @Test
    public void testUserCreation() {
        List<UserData> before = applicationManager.getUserHelper().getUserList();
        UserData user = new UserData(
                            "Morbo",
                            "Annulyator",
                            "New New York City, 12313, Westend",
                            "80993452312",
                            "morbo_annulyator@gmail.com",
                            "test1");
        applicationManager.getUserHelper().createUser(user, true);
        applicationManager.getNavigationHelper().returnToHomePage();
        List<UserData> after = applicationManager.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() + 1);

        Comparator<? super UserData> byId = Comparator.comparingInt(UserData::getId);
        user.setId(after.stream().max(byId).get().getId());
        before.add(user);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}