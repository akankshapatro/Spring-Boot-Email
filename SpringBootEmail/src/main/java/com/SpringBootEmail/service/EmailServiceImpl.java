//Step 4: Creating Service implementation class
package com.SpringBootEmail.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.SpringBootEmail.Entity.EmailDetails;

@Service
public class EmailServiceImpl implements EmailService{
	

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;

	//Method to send a simple email
	public String sendSimpleMail(EmailDetails details) {

		try {
			//creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			//Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			//Sending the mail
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";

		} catch (Exception e) {
			return "Error while Sending Mail";
		}

	}
	
	//Method to send a email with attachment
	public String sendMailWithAttachment(EmailDetails details) {
		//Creating a mime message
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
		try {
			//Setting multipart as true for attachments to be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());
			
			//Adding the attachment
			FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
			mimeMessageHelper.addAttachment(file.getFilename(), file);
			
			//Sending the mail
			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		} catch (MessagingException e) {
			return "Error while sending mail!!!";
		}
		
		
		
		
	}
		

}
