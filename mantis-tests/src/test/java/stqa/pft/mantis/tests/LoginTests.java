package stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import stqa.pft.mantis.application_manager.HttpSession;
import stqa.pft.mantis.model.User;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

    @Test
    public void testLogin() throws IOException {
        User user = new User().withUsername("administrator").withPassword("root");
        HttpSession session = app.newSession();
        assertTrue(session.login(user));
        assertTrue(session.isLoggedInAs(user));
    }
}
