package stqa.pft.addressbook.tests.user;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;
import stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(
                    new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                            .withAddress("New New York City, 12313, Westend")
                            .withMobilePhone("80993452312")
                            .withFirstEmail("morbo_annulyator@gmail.com")
                            .withGroup("test111")
                    , true);
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
                        .withMobilePhone("80123432332")
                        .withFirstEmail("pazuzu_annulyator@gmail.com");
        app.user().modify(user);
        assertThat(app.user().count(), equalTo(before.size()));
        Users after = app.user().all();
        assertThat(after, equalTo(before.withModified(user)));
    }
}
