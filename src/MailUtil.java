import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUtil {


    public void sendMail(String to, String name, String item, String metrics, int quantity, int price){

        final String username = "jnrangarajan@paypalcorp.com";

        final String password = "Fawkes93@Pp";

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.host", "outlook.office365.com");

        props.put("mail.smtp.port", "587");

        System.out.println("In sendmail function");
        Session session = Session.getInstance(props,

                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(username, password);

                    }

                });


        try {


            String from = "jnrangarajan@paypal.com";
            to = to + "@paypal.com";

            System.out.println("Sending mail to: "+to);
//
//            String to = "keraj@paypal.com";
//
//            String name = "Aishwarya";
//
//            String item = "rice";
//
//            String metrics = "kg";
//
//            String quantity = "10";
//
//            String price = "400";

//            String raffleid = "502";

            String text = "";


            text = "<body> <div> <div align=center style=\"background-color:#ffffbb\"> " +

                    "<img src=\"http://smallchangeco.org/wp-content/uploads/2014/05/daan-utsav-Eng-01-300x279.png\" width=\"90\" height=\"90\"/> " +

                    "<h1 style=\"color:#ff6600\"> Charitython Receipt </h1><hr> </div>" +

                    "<div align=center style=\"font-size:20px; padding:5px\" > " +

                    "Dear" + name + ", <br><br> " +

                    "Thanks for being a part of the PayPal Give Charitython! Here is your receipt. <br><br></div>" +

                    "<table border=\"2\" width=\"50%\" align=center>" +

                    "<tr><th>Item</th><th>Quantity</th><th>Cost</th><th>Price</th></tr>" +

                    "<tr><td>" + item + "</td><td>" + quantity + "</td><td>" + metrics + "</td><td>" + price + "</td></tr>" +

                    "<tr><th colspan=4 style=\"text-align:right\">Total Price: " + price + "</th></tr>" +

                    "</table><br>" +

                    "<div style=\"font-size:20px\" align=center>" +

                    "Your contribution will make a huge difference in many lives!<br>Thank you very much! <br><br>" +

                    "Regards, <br>PayPal Give Team <br><br></div>" +

                    "<div align=center style=\"background-color:#ffffbb; font-size:20px; padding-top:10px\">" +

                    "On purchase of items worth more than 500 rupees, you win a raffle ticket for every 500 rupees! <br>" +

                    "You can win exciting prizes if one of your raffle tickets is drawn in the lottery!" +

                    "<h1 style=\"color:#ff6600\"> Donate and Win! </h1></div> </div></body>";

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,

                    InternetAddress.parse(to));

            message.setSubject("PayPal Give - Thank You!");

            message.setSentDate(new Date());

            message.setContent(text, "text/html; charset=utf-8");

            Transport.send(message);


            System.out.println("Done");


        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }

    }

    public static void main(String[] args) throws IOException {
//        MailUtil mu = new MailUtil();
//        mu.sendMail("aabhijitbarve@paypal.com", "Amit Barve", "Combo1", "N/A", 1, 500);
    }
}
