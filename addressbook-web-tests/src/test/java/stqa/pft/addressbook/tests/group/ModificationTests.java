package stqa.pft.addressbook.tests.group;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
import stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.db().groups();
        GroupData group =
                before.iterator().next()
                .withName("test111")
                .withHeader("test2")
                .withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withModified(group)));
        verifyGroupListInUI();
    }

}
