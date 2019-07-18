package stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import stqa.pft.mantis.model.MailMessage;
import stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {
    private long now = System.currentTimeMillis();
    private User user = new User()
            .withUsername(String.format("user%s", now))
            .withPassword("password")
            .withEmail(String.format("user%s@localhost", now));

//    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

//    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

    @Test(enabled = false)
    public void testRegistrationWithLocalMailServer() throws IOException {
        app.registration().start(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = app.remote().findConfirmationLink(mailMessages, user);
        app.registration().finish(confirmationLink, user);

        assertTrue(app.newSession().login(user));
    }

    @Test
    public void testRegistrationWithRemoteMailServer() throws IOException, MessagingException {
        app.remote().createUser(user);
        app.registration().start(user);
        List<MailMessage> mailMessages = app.remote().waitForMail(user, 60000);
        String confirmationLink = app.remote().findConfirmationLink(mailMessages, user);
        app.registration().finish(confirmationLink, user);

        assertTrue(app.newSession().login(user));
    }
}
