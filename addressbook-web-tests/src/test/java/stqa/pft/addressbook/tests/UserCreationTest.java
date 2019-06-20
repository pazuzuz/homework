package stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;

import java.util.Comparator;
import java.util.List;

public class UserCreationTest extends TestBase{

    @Test
    public void testUserCreation() {
        app.goTo().homePage();
        Users before = app.user().all();
        UserData user =
                new UserData()
                        .withFirstName("Morbo")
                        .withLastName("Annulyator")
                        .withAddress("New New York City, 12313, Westend")
                        .withMobile("80993452312")
                        .withEmail("morbo_annulyator@gmail.com")
                        .withGroup("test1")
                ;
        app.user().create(user, true);
        app.goTo().returnToHomePage();
        Users after = app.user().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        MatcherAssert.assertThat(after, CoreMatchers.equalTo(
                before.withAdded(user.withId(after.stream().mapToInt(UserData::getId).max().getAsInt()))));
    }
}