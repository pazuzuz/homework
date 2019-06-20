package stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTest extends TestBase {
    @Test
    public void testUserDeletion(){
        app.goTo().homePage();
        if (! app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(
                    new UserData(
                            "Pazuzu",
                            "Annulyator",
                            "New New York City, 12313, Westend",
                            "80993452312",
                            "morbo_annulyator@gmail.com",
                            "test1"), true);
            app.goTo().returnToHomePage();
        }
        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().selectUser(before.size() - 1);
        app.getUserHelper().deleteSelectedUsers();
        if (app.getUserHelper().isAlertPresent()){
            app.getUserHelper().acceptAlert();
        }
        app.goTo().homePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
