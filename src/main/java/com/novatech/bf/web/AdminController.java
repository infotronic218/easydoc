package com.novatech.bf.web;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novatech.bf.dao.RepositoryRepresentant;
import com.novatech.bf.dao.RepositoryRole;
import com.novatech.bf.dao.RepositoryUser;
import com.novatech.bf.entities.Representant;
import com.novatech.bf.entities.Role;
import com.novatech.bf.entities.User;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private RepositoryRepresentant repRepresent;
	@Autowired
	private RepositoryRole repRole;
	@RequestMapping(value="")
	public String index() {
		return"admin/admin";
	}

	@RequestMapping(value="/delegues/json")
	@ResponseBody
	public List<Representant>getJson(){
		return repRepresent.findAll();
	}
	@RequestMapping(value="/delegues")
	public String AdministrateurDelegue() {
		return "admin/delegue";
	}
	
	@RequestMapping(value="/delegues/{email}/activate")
	@ResponseBody
	@Transactional
	public boolean ActivateAdmin(@PathVariable(name="email")String email) {
		if(repRepresent.existsById(email)) {
			Representant r = repRepresent.getOne(email);
			r.setActive(!r.isActive());
			String body ="", titre ="" ;
			if(r.isActive()) {
			body="<p>Après vérification votre compte a été activé."
					+ " par l'administrateur.</p>"
					+ "<p> Vous pouvez maintenant "
					+ " vous connecter et modifier certaines parties du site.</p>"  
					+ "<a href=\"EAsydocbf.herokuapp.com\"> Faso Easy Doc</a>";
			        titre ="Activation de compte";
			}else {
				body="<p>Votre compte a été désactivé   par l'administrateur.</p>"
						+ "<p> Vous vous n'avez plus l'accès au site à travers votre compte. "
						+ " Pour plus d'informations veuillez contacter l'administrateur du site ..</p>"  
						+ "<a href=\"EAsydocbf.herokuapp.com\"> Faso Easy Doc</a>";
				titre ="Désactivation  de compte";
			}
			try {
				send(email, titre , body,true);
				repRepresent.save(r);
				return true ;
			} catch (MessagingException e) {
				
			}
		}
		return false;
	}
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String to, String subject, String body, boolean html) throws MessagingException {
		MimeMessage message =javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message,true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body,html);
		
		javaMailSender.send(message);
		
	}
}
