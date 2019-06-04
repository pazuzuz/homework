package stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import stqa.pft.addressbook.model.NewUserData;

public class UserModificationTest extends TestBase {
    @Test
    public void testUserModification(){
        if (! applicationManager.getUserHelper().isThereAUser()) {
            applicationManager.getUserHelper().createUser(
                 new NewUserData(
                         "Pazuzu",
                         "Annulyator",
                         "New New York City, 12313, Westend",
                         "80993452312",
                         "morbo_annulyator@gmail.com",
                         "test1"), true);
        }
        applicationManager.getNavigationHelper().returnToHomePage();
        applicationManager.getUserHelper().initModifyUser();
        applicationManager.getUserHelper().fillUserForm(
                new NewUserData(
                        "Pazuzu",
                        "Annulyator",
                        "New New York City, 12313, Westend",
                        "80993452312",
                        "morbo_annulyator@gmail.com",
                        null), false);
        applicationManager.getUserHelper().submitUpdateUserForm();
        applicationManager.getNavigationHelper().returnToHomePage();
    }
}
