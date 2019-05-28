package stqa.pft.addressbook.tests;

import entity.User;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.NewUserData;

public class UserCreationTest extends TestBase{

    @Test
    public void testUserCreation() {
        applicationManager.getSessionHelper().loginAs(User.ADMIN);
        applicationManager.getUserHelper().initAddNewUser();
        applicationManager.getUserHelper().fillNewUserForm(
                new NewUserData(
                        "Morbo",
                        "Annulyator",
                        "New New York City, 12313, Westend",
                        "80993452312",
                        "morbo_annulyator@gmail.com"));
        applicationManager.getUserHelper().submitNewUserForm();
        applicationManager.getNavigationHelper().returnToHomePage();
    }
}