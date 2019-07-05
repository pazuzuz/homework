package stqa.pft.addressbook.tests.user;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;
import stqa.pft.addressbook.tests.TestBase;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AddUserToGroup extends TestBase {

    @BeforeClass
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("some_group"));
        }

        if (app.db().users().size() == 0){
            app.goTo().homePage();
            app.user().create(new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                    , true);
        }
    }

    @Test
    public void testAddUserToGroup(){
        Groups groups = app.db().groups();
        Users users = app.db().users();
        Set<GroupData> diff = users.iterator().next().getGroups().stream().filter(groupss -> !groups.contains(groupss)).collect(Collectors.toSet());
//        Set<Groups> diff = users.stream().map(UserData::getGroups).filter(groupData -> !groupData.contains(groups)).collect(Collectors.toSet());
        System.out.println(users.iterator().next().getGroups());
        System.out.println( "diff: " + diff);


//        app.goTo().homePage();
//        app.user().selectUserWithoutAllGroups(users, groups);
//        app.user().selectGroup();
//        app.user().addToGroup();
    }

}
