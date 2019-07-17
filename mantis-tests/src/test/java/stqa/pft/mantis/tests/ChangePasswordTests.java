package stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;

public class ChangePasswordTests extends TestBase {
    private long now = System.currentTimeMillis();
    private String user = String.format("user%s", now);
    private String password = "password";
    private String email = String.format("user%s@localhost", now);

    @BeforeMethod
    public void ensurePreconditions() throws MessagingException {
        if (app.db().usersWithDefaultPassword().size() == 0){
            app.user().register(user, password, email);
        }
    }

    @Test
    public void testChangePassword(){

    }
}
