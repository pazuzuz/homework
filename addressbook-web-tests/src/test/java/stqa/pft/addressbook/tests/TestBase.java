package stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import stqa.pft.addressbook.application_manager.ApplicationManager;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    private Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] o){
        logger.info("Start test  " + method.getName() + " with parameters " + Arrays.asList(o));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method){
        logger.info("Stop test  " + method.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")){
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups,
                    equalTo(dbGroups.stream()
                            .map((g) -> new GroupData()
                                    .withId(g.getId())
                                    .withName(g.getName()))
                            .collect(Collectors.toSet())));
        }
    }

    public void verifyUserListInUI() {
        if (Boolean.getBoolean("verifyUI")){
            Users dbUsers = app.db().users();
            Users uiUsers = app.user().all();
            assertThat(uiUsers,
                    equalTo(dbUsers.stream()
                            .map((u) -> new UserData()
                                    .withId(u.getId())
                                    .withFirstName(u.getFirstName())
                                    .withLastName(u.getLastName())
                                    .withAddress(u.getAddress()))
                            .collect(Collectors.toSet())));
        }
    }

    public void verifyUserGroupsListInUI(UserData user) {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().userInGroups(user);
            app.goTo().homePage();
            app.user().details(user);
            Groups uiGroups = app.user().groups();
            assertThat(uiGroups,
                    equalTo(dbGroups.stream()
                            .map((group) -> new GroupData()
                                    .withId(group.getId())
                                    .withName(group.getName()))
                            .collect(Collectors.toSet())));
        }
    }
}
