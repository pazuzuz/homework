package stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
                        .withMobilePhone("80993452312")
                        .withHomePhone("5375837")
                        .withWorkPhone("545634")
                        .withEmail("morbo_annulyator@gmail.com")
                        .withGroup("test1")
                ;
        app.user().create(user, true);
        assertThat(app.user().count() ,equalTo(before.size() + 1));
        Users after = app.user().all();
        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt(UserData::getId).max().getAsInt()))));
    }
}