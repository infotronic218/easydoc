package com.novatech.bf.services;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novatech.bf.dao.RepositoryDemande;
import com.novatech.bf.dao.RepositoryDocument;
import com.novatech.bf.dao.RepositoryInformation;
import com.novatech.bf.dao.RepositoryType;
import com.novatech.bf.entities.Demande;
import com.novatech.bf.entities.Document;
import com.novatech.bf.entities.Information;
import com.novatech.bf.entities.Type;

@Service
@Transactional
public class CasierMetier {
	@Autowired
	private RepositoryType typeRepo;
	@Autowired
	private RepositoryDemande demandRepo;
	@Autowired
	private RepositoryDocument docRepo;
	@Autowired
	private RepositoryInformation infoRepo;
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	public boolean Enregister() throws IOException, MessagingException {
		if(!typeRepo.existsById("CASIER")) {
		    Type type = new Type("CASIER","Pour tout dossier qui concerne le casier judiciaire");
		    typeRepo.save(type);
		}
		
		Information information = (Information) session.getAttribute("information");
		Document document = (Document) session.getAttribute("document");
		String email = (String) session.getAttribute("email");
		System.out.println(information);
		System.out.println(document);
		System.out.print(email);
		if(information!=null && document!=null && email!=null) {
			docRepo.save(document);
			infoRepo.save(information);
			Demande demande = new Demande();
			demande.setType(typeRepo.findByName("CASIER"));
			demande.setDate(new Date());
			demande.setEmail(email);
			demande.setDocument(document);
			demande.setInformation(information);
			String pass = generatePass(10);
			demande.setPassword(encoder.encode(pass));
			demandRepo.save(demande);
			String body="Votre demande de casier judicaire a"
					+ " été enregistré avec succès. Le code de vore demande est : "+pass
					+ " .; Merci pour votre confiance !";
			send(email, "Service Casier judiciaire", "Confirmation de demande de caiser judiciaire", body);
			return true;
			
		}else {
		  throw	new  RuntimeException("Donnée non valide !");
		}
		
	}
	
	
	public String generatePass(int length) {
		char [] list= {'0','1','3','4','5','6','7',
				'8','9','A','B','C','D','E','F','G',
				'H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String t="";
		for(int i= 0 ; i< length; i++) {
        	t+=String.valueOf(list[new Random().nextInt(list.length-1)]);
        }
		return t;
	}
	
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
