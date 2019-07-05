package stqa.pft.addressbook.tests.group;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
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

    @Test(dataProvider = "validGroupsFromJSONFile")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
        verifyGroupListInUI();
    }

    @Test(enabled = false)
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData group = new GroupData().withName("test1'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before));
    }

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
        list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
        list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromCSVFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))){
            String line = reader.readLine();
            while (line != null){
                String[] split = line.split(";");
                list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
                line = reader.readLine();
            }
            return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromXMLFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
            StringBuilder xml = new StringBuilder();
            String line = reader.readLine();
            while (line != null){
                xml.append(line);
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>)xStream.fromXML(xml.toString());
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJSONFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))){
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null){
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json.toString(), new TypeToken<List<GroupData>>(){}.getType());  // List<GroupData>.class
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }
}