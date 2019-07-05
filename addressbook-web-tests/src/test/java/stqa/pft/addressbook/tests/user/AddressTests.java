package stqa.pft.addressbook.tests.user;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                                .withName("test 1")
                                .withHeader("test")
                                .withFooter("test")
            );
        }

        if (app.db().users().size() == 0) {
            app.goTo().homePage();
            app.user().create(
                    new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                            .withAddress("    New New York City,       12313, Westend")
                            .inGroups(app.db().groups().iterator().next())
                    , true);
        }
    }

    @Test
    public void testUserAddress(){
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getAddress(), equalTo(cleaned(userInfoFromEditForm.getAddress())));
    }

    public static String cleaned(String address){
        return address
                .replaceAll("\\s\\s+", " ")
                .replaceAll("^\\s*", "");
    }
}
