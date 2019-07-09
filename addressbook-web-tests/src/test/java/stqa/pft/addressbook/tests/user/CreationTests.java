package stqa.pft.addressbook.tests.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;
import stqa.pft.addressbook.tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreationTests extends TestBase {
    private File photo = new File("src/test/resources/morbo.png");

    @BeforeTest
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test_create"));
        }
    }

    @Test(dataProvider = "validUsersFromJSONFile")
    public void testUserCreation(UserData user) {
        app.goTo().homePage();
        Groups groups = app.db().groups();
        Users before = app.db().users();
        user.withPhoto(photo).inGroups(groups.iterator().next());
        app.user().create(user, true);
        assertThat(app.user().count() ,equalTo(before.size() + 1));
        Users after = app.db().users();

        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt(UserData::getId).max().getAsInt()))));
        verifyUserListInUI();
    }

    @Test(enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new UserData()
                                    .withFirstName("firstname1")
                                    .withLastName("lastname1")
                                    .withMobilePhone("65364587")
                                    .inGroups(app.db().groups().iterator().next())
                                    .withPhoto(photo)});
        list.add(new Object[] {new UserData()
                                    .withFirstName("firstname2")
                                    .withLastName("lastname2")
                                    .withMobilePhone("65364587")
                                    .inGroups(app.db().groups().iterator().next())
                                    .withPhoto(photo)});
        list.add(new Object[] {new UserData()
                                    .withFirstName("firstname3")
                                    .withLastName("lastname3")
                                    .withMobilePhone("65364587")
                                    .inGroups(app.db().groups().iterator().next())
                                    .withPhoto(photo)});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validUsersFromCSVFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")))){
            String line = reader.readLine();
            while (line != null){
                String[] split = line.split(";");
                list.add(new Object[] {new UserData()
                        .withFirstName(split[0])
                        .withLastName(split[1])
                        .withFirstEmail(split[2])
                        .withMobilePhone(split[3])
                        .inGroups(app.db().groups().iterator().next())
                        .withPhoto(photo)});
                line = reader.readLine();
            }
            return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validUsersFromXMLFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.xml")))){
            StringBuilder xml = new StringBuilder();
            String line = reader.readLine();
            while (line != null){
                xml.append(line);
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(UserData.class);
            List<UserData> groups = (List<UserData>)xStream.fromXML(xml.toString());
            return groups.stream()
                    .map((g) -> new Object[] {g.inGroups(app.db().groups().iterator().next()).withPhoto(photo)})
                    .collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validUsersFromJSONFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.json")))){
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null){
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<UserData> groups = gson.fromJson(json.toString(), new TypeToken<List<UserData>>(){}.getType());  // List<GroupData>.class
            return groups.stream()
                    .map((g) -> new Object[] {g.inGroups(app.db().groups().iterator().next()).withPhoto(photo)})
                    .collect(Collectors.toList()).iterator();
        }
    }
}