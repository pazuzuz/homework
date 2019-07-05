package stqa.pft.addressbook.tests.user;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().users().size() == 0) {
            app.goTo().homePage();
            app.user().create(
                    new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                            .withMobilePhone("8-099-345-23-12")
                            .withHomePhone("+38 (099)")
                            .withWorkPhone("33 33 33")
                    , true);
        }
    }

    @Test
    public void testUserPhones(){
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
    }

    private String mergePhones(UserData user) {
        return Arrays.asList(user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(PhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned(String phone){
        return phone
                .replaceAll("\\s", "")
                .replaceAll("[-()]","");
    }
}
