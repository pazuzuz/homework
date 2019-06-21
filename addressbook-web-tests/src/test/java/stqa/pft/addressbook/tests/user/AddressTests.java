package stqa.pft.addressbook.tests.user;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(
                    new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                            .withAddress("    New New York City,       12313, Westend")
                            .withGroup("test1")
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
