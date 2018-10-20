package com.novatech.bf.services;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Configuration
public class MailSender {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String to,String title, String subject, String body) throws MessagingException {
		MimeMessage message =javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message,true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body);
		javaMailSender.send(message);
		
	}
	
	public void sendReset(String to,String title, String subject, String body, String address) throws MessagingException {
		MimeMessage message =javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message,true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body);
		InternetAddress net = new InternetAddress();
		net.setAddress(address);
		helper.setCc(net);
	    
		javaMailSender.send(message);
		
	}
	

}
