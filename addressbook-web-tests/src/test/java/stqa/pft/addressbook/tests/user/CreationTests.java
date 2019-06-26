package stqa.pft.addressbook.tests.user;

import org.testng.annotations.Test;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;
import stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class CreationTests extends TestBase {

    @Test
    public void testUserCreation() {
        app.goTo().homePage();
        Users before = app.user().all();
        File photo = new File("src/test/resources/morbo.png");
        UserData user =
                new UserData()
                        .withFirstName("Morbo")
                        .withLastName("Annulyator")
                        .withAddress("New New York City, 12313, Westend")
                        .withMobilePhone("80993452312")
                        .withHomePhone("5375837")
                        .withWorkPhone("545634")
                        .withFirstEmail("morbo_annulyator@gmail.com")
                        .withGroup("test1")
                        .withPhoto(photo)
                ;
        app.user().create(user, true);
        assertThat(app.user().count() ,equalTo(before.size() + 1));
        Users after = app.user().all();
        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt(UserData::getId).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/morbo.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}