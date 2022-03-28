//Step 4: Creating Service Interface 
package com.SpringBootEmail.service;

import com.SpringBootEmail.Entity.EmailDetails;

public interface EmailService {
	
	//Method to send a simple email
	String sendSimpleMail(EmailDetails details);
	
	//Method to send a mail with attachment
	String sendMailWithAttachment(EmailDetails details);
	
}
