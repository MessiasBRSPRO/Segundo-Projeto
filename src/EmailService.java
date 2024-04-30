import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailService {
    private int codeVerification;
    private final String smtpServer = "smtp.gmail.com";

    public EmailService(){
        this.codeVerification = new Random().nextInt(999999) ;
    }
    public void sendVerificationCode(String mailDestiny){
        final String mailRemetent = "meddisme@gmail.com"; //Support mail
        final String passwordFrom = "hxbkizlaelojzzxs";
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", 587);

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailRemetent, passwordFrom);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailRemetent));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDestiny));
            message.setSubject("Your verification Code");
            message.setText("Verification Code is :"+this.codeVerification +
                    "\n" + "if you insert the code in application, you can close this page!");
            Transport.send(message);
            System.out.println("an Email has send");
        }catch (MessagingException e){
            System.out.println("an exception has occurred:"+e.getMessage());
        }

    }
}
