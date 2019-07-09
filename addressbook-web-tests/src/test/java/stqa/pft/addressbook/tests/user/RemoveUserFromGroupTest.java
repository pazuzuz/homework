package stqa.pft.addressbook.tests.user;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;
import stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveUserFromGroupTest extends TestBase {
    private UserData user;

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
                            .inGroups(app.db().groups().iterator().next())
                    , true);
        }

        Users users = app.db().users();
        for (UserData user: users ) {
            if (!user.getGroups().isEmpty()){
                this.user = user;
                break;
            }
        }

        if (this.user == null){
            this.user = app.db().users().iterator().next();
            app.goTo().homePage();
            app.user().addToGroup(this.user, app.db().groups().iterator().next());
        }
    }

    @Test
    public void testAddUserToGroup() {
        Groups before = app.db().userInGroups(user);
        app.goTo().homePage();
        GroupData removedGroup = before.iterator().next();
        app.user().removeFromGroup(user, removedGroup);
        Groups after = app.db().userInGroups(user);

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.withRemovedGroup(removedGroup)));

        verifyUserGroupsListInUI(user);
    }
}
