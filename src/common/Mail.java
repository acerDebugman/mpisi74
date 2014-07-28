package common;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private String host = "192.168.50.171"; //发件人使用发邮件的电子信箱服务器
	private String port = "10025";
	private String userId = "mpisi\\HRMS";
	private String password = "IDpt463";
	
	private String from = "HRMS@mpisi.com"; //发邮件的出发地（发件人的信箱）
	private String to = ""; //发邮件的目的地（收件人信箱）
	private String subject = ""; // 邮件标题
	private String content = ""; // 邮件内容
    
    public void send(){
	    try{
//		    // Get system properties
//		    Properties props = System.getProperties();
//		    // Setup mail server
//		    props.put("mail.smtp.host", host);
//		    
//		    props.put("mail.smtp.connectiontimeout","1000");
//		    props.put("mail.smtp.timeout","2000");
//		    // Get session
//		    props.put("mail.smtp.auth", "true");
//		    //props.put("mail.smtp.starttls.enable","true");
//		    
//		    props.put("mail.smtp.port", port);
//
//		    MyAuthenticator myauth = new MyAuthenticator(userId, password);
//		    Session session = Session.getDefaultInstance(props, myauth);
//
//		    session.setDebug(false);
//		    // Define message
//		    MimeMessage message = new MimeMessage(session);
//		    
//		    // Set the from address
//		    message.setFrom(new InternetAddress(from));
//		    new InternetAddress();
//			// Set the to address
//		    //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		    InternetAddress[] toList = InternetAddress.parse(to);
//		    message.addRecipients(Message.RecipientType.TO, toList);
//		    // Set the subject
//		    message.setSubject(subject);
//		    // Set the content
//		    message.setText(content);
//		    message.saveChanges();
//		    
//		    Transport.send(message);
		    
		    System.out.println("mail send success"); 
	    }catch(Exception ex){
	    	System.out.println(ex.getMessage());
	    }
    }
   
    public void sendTextHtml(){
	    try{
		    // Get system properties
//		    Properties props = System.getProperties();
//		    // Setup mail server
//		    props.put("mail.smtp.host", host);
//		    
//		    props.put("mail.smtp.connectiontimeout","1000");
//		    props.put("mail.smtp.timeout","2000");
//		    // Get session
//		    props.put("mail.smtp.auth", "true");
//		    //props.put("mail.smtp.starttls.enable","true");
//		    
//		    props.put("mail.smtp.port", port);
//
//		    MyAuthenticator myauth = new MyAuthenticator(userId, password);
//		    Session session = Session.getDefaultInstance(props, myauth);
//
//		    session.setDebug(false);
//		    // Define message
//		    MimeMessage message = new MimeMessage(session);
//		    
//		    // Set the from address
//		    message.setFrom(new InternetAddress(from));
//		    new InternetAddress();
//			// Set the to address
//		    //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		    InternetAddress[] toList = InternetAddress.parse(to);
//		    message.addRecipients(Message.RecipientType.TO, toList);
//		    // Set the subject
//		    message.setSubject(subject);
//		    //set to html, so link 
//		    message.setContent(content, "text/html");
//		    //message.setText(content, "text/html");
//		    // Set the content
//		    //message.setText(content);
//		    message.saveChanges();
//		    
//		    Transport.send(message);
		    
		    System.out.println("mail send success"); 
	    }catch(Exception ex){
	    	System.out.println("email exception msg:" + ex.getMessage());
	    }
    }

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
