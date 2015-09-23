//import java.io.IOException;
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class MailUtil {
//
//	public static void main(String[] args) throws IOException {
//
//		final String username = "jnrangarajan@paypalcorp.com";
//		final String password = "Fawkes93@Pp";
//
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "outlook.office365.com");
//		props.put("mail.smtp.port", "587");
//
//		Session session = Session.getInstance(props,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username, password);
//					}
//				});
//
//		try {
//
//			String from = "jnrangarajan@paypal.com";
//			String to = "aabhijitbarve@paypal.com";
//			String name = "Aishwarya Kaneri";
//			String item = "rice";
//			String metrics = "kg";
//			String quantity = "10";
//			String price = "400";
//			String raffleid = "502";
//			String text = "";
//
//			text = "Hi "
//					+ name
//					+ "!\n"
//					+ "Thanks for being a part of PayPal Give Charitython!\n\n You have bought "
//					+ quantity + metrics + " of " + item + ", worth " + price + " rupees.\n"
//					+ "Your transaction id is " + raffleid
//					+ ". Your contribution will make a huge difference in many lives!\n\n"
//					+ "Thank you very much! \n\n Regards, \n PayPal Give Team";
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(from));
//			message.setRecipients(Message.RecipientType.TO,
//					InternetAddress.parse(to));
//			message.setSubject("PayPal Give - Thank You!");
//			message.setText(text);
//
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
