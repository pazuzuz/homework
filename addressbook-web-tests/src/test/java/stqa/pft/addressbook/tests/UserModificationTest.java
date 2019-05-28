package stqa.pft.addressbook.tests;

import entity.User;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.NewUserData;

public class UserModificationTest extends TestBase {
    @Test
    public void testUserModification(){
        applicationManager.getSessionHelper().loginAs(User.ADMIN);
        applicationManager.getUserHelper().initModifyUser();
        applicationManager.getUserHelper().updateUserForm(
                new NewUserData(
                        "Pazuzu",
                        "Annulyator",
                        "New New York City, 12313, Westend",
                        "80993452312",
                        "morbo_annulyator@gmail.com"));
        applicationManager.getUserHelper().submitUpdateUserForm();
        applicationManager.getNavigationHelper().returnToHomePage();
    }
}
