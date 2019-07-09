package stqa.pft.addressbook.tests.user;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;
import stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddUserToGroupTest extends TestBase {
    private UserData user;
    private Set<GroupData> notAddedGroups;

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

        Groups groups = app.db().groups();
        Users users = app.db().users();
        for (UserData user: users ) {
            notAddedGroups = groups.stream().filter(group -> !user.getGroups().contains(group)).collect(Collectors.toSet());
            if (!notAddedGroups.isEmpty()){
                 this.user = user;
                 break;
            }
        }

        if (notAddedGroups.isEmpty()){
            app.goTo().homePage();
            app.user().create(new UserData()
                            .withFirstName("Morbo")
                            .withLastName("Annulyator")
                    , true);
            this.user = app.db().users().stream().max(Comparator.comparing(UserData::getId)).get();
            notAddedGroups = app.db().groups();
        }
    }

    @Test
    public void testAddUserToGroup() {
        Groups before = app.db().userInGroups(user);
        app.goTo().homePage();
        GroupData addedGroup = notAddedGroups.iterator().next();
        app.user().addToGroup(user, addedGroup);
        app.goTo().homePage();
        Groups after = app.db().userInGroups(user);

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAddedGroup(addedGroup)));

        verifyUserGroupsListInUI(user);
    }

}
