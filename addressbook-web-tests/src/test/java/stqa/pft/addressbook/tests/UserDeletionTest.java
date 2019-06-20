package stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;

public class UserDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(
                    new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                            .withAddress("New New York City, 12313, Westend")
                            .withMobile("80993452312")
                            .withEmail("morbo_annulyator@gmail.com")
                            .withGroup("test1")
                    , true);
            app.goTo().returnToHomePage();
        }
    }

    @Test
    public void testUserDeletion(){
        Users before = app.user().all();
        UserData deletedUser = before.iterator().next();
        app.user().delete(deletedUser);
        app.goTo().homePage();
        Users after = app.user().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedUser)));
    }
}
