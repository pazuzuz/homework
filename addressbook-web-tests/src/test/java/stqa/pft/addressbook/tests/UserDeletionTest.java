package stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserDeletionTest extends TestBase {
    @Test
    public void testUserDeletion(){
        if (! applicationManager.getUserHelper().isThereAUser()) {
            applicationManager.getUserHelper().createUser(
                    new UserData(
                            "Pazuzu",
                            "Annulyator",
                            "New New York City, 12313, Westend",
                            "80993452312",
                            "morbo_annulyator@gmail.com",
                            "test1"), true);
        }
        applicationManager.getNavigationHelper().returnToHomePage();
        List<UserData> before = applicationManager.getUserHelper().getUserList();
        applicationManager.getUserHelper().selectUser(before.size() - 1);
        applicationManager.getUserHelper().deleteSelectedUsers();
        if (applicationManager.getUserHelper().isAlertPresent()){
            applicationManager.getUserHelper().acceptAlert();
        }
        applicationManager.getNavigationHelper().goToHomePage();
        List<UserData> after = applicationManager.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
