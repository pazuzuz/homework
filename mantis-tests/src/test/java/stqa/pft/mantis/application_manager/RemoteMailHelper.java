package stqa.pft.mantis.application_manager;

import org.apache.commons.net.telnet.TelnetClient;
import ru.lanwen.verbalregex.VerbalExpression;
import stqa.pft.mantis.model.MailMessage;
import stqa.pft.mantis.model.User;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoteMailHelper {
    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailServer;

    public RemoteMailHelper(ApplicationManager app){
        this.app = app;
        this.telnet = new TelnetClient();
        this.mailSession = Session.getDefaultInstance(System.getProperties());
    }

    public boolean doesUserExist(String name) {
        initTelnetSession();
        write("verify " + name);
        String result = readUtil("exist");
        closeTelnetSession();
        return result.trim().equals("User " + name + " exist");
    }

    public void createUser(User user){
        initTelnetSession();
        write("adduser " + user.getUsername() + " " + user.getPassword());
        String result = readUtil("User " + user.getUsername() + " added");
        closeTelnetSession();
    }

    public void deleteUser(String name){
        initTelnetSession();
        write("deluser " + name);
        String result = readUtil("User" + name + " deleted");
        closeTelnetSession();
    }

    private void initTelnetSession(){
        this.mailServer = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty(("mailserver.adminlogin"));
        String password = app.getProperty(("mailserver.adminpassword"));

        try {
            this.telnet.connect(this.mailServer, port);
            this.in = this.telnet.getInputStream();
            this.out = new PrintStream(this.telnet.getOutputStream());
        }catch (Exception ex){
            ex.printStackTrace();
        }

        //first attempt doesn't work
        readUtil("Login id:");
        write("");
        readUtil("Password:");
        write("");

        //second attempt
        readUtil("Login id:");
        write(login);
        readUtil("Password:");
        write(password);

        //Read welcome message
        readUtil("Welcome " + login + ". HELP for a list of commands");
    }

    private String readUtil(String pattern){
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true){
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar){
                    if (sb.toString().endsWith(pattern)){
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    private void write(String value){
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void closeTelnetSession(){
        write("quit");
    }

    private void drainEmail(User user) throws MessagingException {
        Folder inbox = openInbox(user);
        for (Message message: inbox.getMessages()) {
            message.setFlag(Flags.Flag.DELETED, true);
        }
        closeFolder(inbox);
    }

    private Folder openInbox(User user) throws MessagingException {
        store = mailSession.getStore("pop3");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        store.connect(mailServer, user.getUsername(), user.getPassword());
        Folder folder = store.getDefaultFolder().getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }

    private void closeFolder(Folder folder) throws MessagingException {
        folder.close(true);
        store.close();
    }

    public List<MailMessage> waitForMail(User user, long timeout) throws MessagingException {
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() < now + timeout){
            List<MailMessage> allMail = getAllMail(user);
            if (allMail.size() > 0){
                return allMail;
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }

    private List<MailMessage> getAllMail(User user) throws MessagingException {
        Folder inbox = openInbox(user);
        List<MailMessage> messages = Arrays.stream(inbox.getMessages()).map(RemoteMailHelper::toModelMail).collect(Collectors.toList());
        closeFolder(inbox);
        return messages;
    }

    public static MailMessage toModelMail(Message m){
        try {
            return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
        }catch (MessagingException | IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, User user) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(user.getEmail())).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

}