package stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import stqa.pft.addressbook.model.NewUserData;

public class UserCreationTest extends TestBase{

    @Test
    public void testUserCreation() {
        applicationManager.getUserHelper().createUser(
                new NewUserData(
                        "Morbo",
                        "Annulyator",
                        "New New York City, 12313, Westend",
                        "80993452312",
                        "morbo_annulyator@gmail.com",
                        "test1"), true);
        applicationManager.getNavigationHelper().returnToHomePage();
    }
}