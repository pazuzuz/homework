package stqa.pft.mantis.application_manager;

import stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }


    public void register(String user, String password, String email) throws MessagingException {
        app.remote().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.remote().waitForMail(user, password, 60000);
        String confirmationLink = app.remote().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
    }
}
