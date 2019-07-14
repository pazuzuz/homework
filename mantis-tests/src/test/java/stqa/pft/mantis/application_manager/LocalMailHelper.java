package stqa.pft.mantis.application_manager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LocalMailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public LocalMailHelper(ApplicationManager app){
        this.app = app;
        wiser = new Wiser();
    }

    public List<MailMessage> waitForMail(int count, long timeout){
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout){
            if (wiser.getMessages().size() >= count){
                return wiser.getMessages().stream().map(LocalMailHelper::toModelMail).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No Email :(");
    }

    public static MailMessage toModelMail(WiserMessage message){
        try {
            MimeMessage mimeMessage = message.getMimeMessage();
            return new MailMessage(mimeMessage.getAllRecipients()[0].toString(), (String) mimeMessage.getContent());
        }catch (MessagingException ex){
            ex.printStackTrace();
            return null;
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void start(){wiser.start();}

    public void stop(){wiser.stop();}
}
