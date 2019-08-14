package stqa.pft.addressbook.tests.user;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmailTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().users().size() == 0) {
            app.goTo().homePage();
            app.user().create(
                    new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                            .withFirstEmail("    morbo_annulyator@gmail.com")
                            .withThirdEmail("morbo_annulyator@gmail.com    ")
                    , true);
        }
    }

    @Test
    public void testUserEmails(){
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));
    }

    private String mergeEmails(UserData user) {
        return Arrays.asList(user.getFirstEmail(), user.getSecondEmail(), user.getThirdEmail())
                .stream().filter((s) -> !s.equals(""))
                .map(EmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned(String email){
        return email
                .replaceAll("\\s\\s+", "");
    }
}
