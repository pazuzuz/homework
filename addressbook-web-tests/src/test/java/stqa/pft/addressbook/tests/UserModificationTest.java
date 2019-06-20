package stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;

public class UserModificationTest extends TestBase {
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
    public void testUserModification(){
        Users before = app.user().all();
        UserData user =
                before.iterator().next()
                        .withFirstName("Pazuzu")
                        .withLastName("Annulyator")
                        .withAddress("New New York City, 12313, Westend")
                        .withMobile("80123432332")
                        .withEmail("pazuzu_annulyator@gmail.com");
        app.user().modify(user);
        app.goTo().returnToHomePage();
        Users after = app.user().all();
        Assert.assertEquals(after.size(), before.size());
        MatcherAssert.assertThat(before, CoreMatchers.equalTo(after.withModified(user)));
    }
}
