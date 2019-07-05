package stqa.pft.addressbook.tests.user;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;
import stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("group"));
        }

        if (app.db().users().size() == 0) {
            app.goTo().homePage();
            app.user().create(
                    new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                            .withAddress("New New York City, 12313, Westend")
                            .withMobilePhone("80993452312")
                            .withFirstEmail("morbo_annulyator@gmail.com")
                            .inGroups(app.db().groups().iterator().next())
                    , true);
        }
    }

    @Test
    public void testUserModification(){
        app.goTo().homePage();
        Users before = app.db().users();
        UserData user =
                before.iterator().next()
                        .withFirstName("Pazuzu")
                        .withLastName("Annulyator")
                        .withAddress("New New York City, 12313, Westend")
                        .withMobilePhone("80123432332")
                        .withFirstEmail("pazuzu_annulyator@gmail.com");
        app.user().modify(user);
        assertThat(app.user().count(), equalTo(before.size()));
        Users after = app.db().users();
        assertThat(after, equalTo(before.withModified(user)));
        verifyUserListInUI();
    }
}
