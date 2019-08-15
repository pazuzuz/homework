package stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.pft.mantis.application_manager.HttpSession;
import stqa.pft.mantis.model.User;
import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
    private long now = System.currentTimeMillis();
    private User user = new User()
            .withUsername(String.format("user%s", now))
            .withPassword("password")
            .withEmail(String.format("user%s@localhost", now));

    @BeforeMethod
    public void ensurePreconditions() throws MessagingException, IOException {
        if (app.db().notAdminUsers().size() == 0){
            app.user().register(user);
        }

        if (app.db().usersWithDefaultPassword().size() == 0){
            app.db().restoreDefaultPassword(app.db().notAdminUsers().iterator().next());
        }

        user = app.db().usersWithDefaultPassword().iterator().next().withPassword("password");

        HttpSession session = app.newSession();
//        System.out.println(user);
        assertTrue(session.login(user));
        assertTrue(session.isLoggedInAs(user));
    }


    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.admin().signIn();
        app.admin().resetUserPassword(user);
        app.user().changePassword(user, "password1");
        HttpSession session = app.newSession();
        assertTrue(session.login(user));
        assertTrue(session.isLoggedInAs(user));
//        User user = new User().withUsername("user1565795600599").withPassword("password");
//        app.remote().initTelnetSession();
//        app.remote().openInbox(user);
    }
}
