package com.novatech.bf.web;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.novatech.bf.dao.RepositoryDemande;
import com.novatech.bf.dao.RepositoryType;
import com.novatech.bf.entities.Demande;
import com.novatech.bf.entities.Document;
import com.novatech.bf.entities.Information;
import com.novatech.bf.services.Metier;
import com.novatech.bf.services.ContryCodeManager;

@Controller
@RequestMapping(value="/services/certificat")
public class Certificat {

	@Autowired
	private Metier casierMetier;
	
	@Autowired
	private ContryCodeManager codeManager;
	@Autowired
	private HttpSession session;
	@Autowired
	private RepositoryType repoType;
	@Autowired
	private RepositoryDemande demRepo;
	
	
	
	@RequestMapping(value="")
	public String home(Model model) {
		model.addAttribute("current","SERVICES");
		return "certificat/certificat";
	}
	
	@RequestMapping(value="/demande")
	public String demande(Model model) {
		model.addAttribute("current","SERVICES");
		model.addAttribute("etape",1);
		model.addAttribute("information", new Information());
		model.addAttribute("countries", codeManager.getContries());
		return "certificat/demande";
	}
	
	
	@RequestMapping(value="/demande/documents", method=RequestMethod.POST)
	public String demandeDocuemts(Model model, 
			Information information,
			@RequestParam(name="email") String email, BindingResult result) {
		
		if(result.hasErrors() || email==null) {
			model.addAttribute("etape",1);
			model.addAttribute("information", information);
			model.addAttribute("countries", codeManager.getContries());
			return "certificat/demande";
			
		}
		session.setAttribute("email",email );
		session.setAttribute("information", information);
		model.addAttribute("document", new Document());
		model.addAttribute("current","SERVICES");
		model.addAttribute("countries", codeManager.getContries());
		model.addAttribute("etape",2);
		return "certificat/demande";
	}
	
	@RequestMapping(value="/demande/confirmation",method=RequestMethod.POST)
	public String demandeConfirmation(Model model, Document document, BindingResult result) throws IOException {
		if(result.hasErrors() || document==null || document.getFile()==null) {
			model.addAttribute("current","SERVICES");
			model.addAttribute("etape",3);
			return "certificat/demande";
		}
		
		document.setFichier(document.getFile().getBytes());
		session.setAttribute("document", document);
		model.addAttribute("current","SERVICES");
		model.addAttribute("etape",3);
		model.addAttribute("countries", codeManager.getContries());
		return "certificat/demande";
	}
	
	@RequestMapping(value="/demande/save", method=RequestMethod.POST)
	public String demandeSave(Model model) {
		model.addAttribute("current","SERVICES");
		try {
			casierMetier.saveCertificatDemand();
			model.addAttribute("etape",4);
			System.out.println("Donnée enregister");
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
			model.addAttribute("countries", codeManager.getContries());
			model.addAttribute("etape",4);
			return "certificat/demande";
		}
		
		model.addAttribute("countries", codeManager.getContries());
		return "certificat/demande";
	}
	
	@RequestMapping(value="/suivre")
	public String suivre(Model model) {
		model.addAttribute("current","SERVICES");
		model.addAttribute("ACTION", "connection");
		return "certificat/suivre";
	}
	
	@RequestMapping(value="/verifier", method=RequestMethod.POST)
	public String suivreMademande(Model model, @RequestParam(name="email")String email ,@RequestParam(name="password") String password ) {
		Demande d = casierMetier.findByEmailAndPassword(email, password);
		System.out.println(d);
		System.out.println(" "+email +" "+password);
		if(d==null || !d.getType().getName().equals("CASIER")) {
			model.addAttribute("current","SERVICES");
			model.addAttribute("current","SERVICES");
			model.addAttribute("ACTION", "connection");
			model.addAttribute("error", "Aucune demande n'a été trouvé ! Veuillez vérifier vos coordonnées et réessayer.");
			return "certificat/suivre";
		}else {
			model.addAttribute("demande",d);
			model.addAttribute("ACTION", "connected");
		}
		model.addAttribute("current","SERVICES");
		return "certificat/suivre";
	}
	
	@RequestMapping(value="/mademande")
	public String Mademande(Model model) {
		model.addAttribute("current","SERVICES");
		return "certificat/suivre";
	}
	
}
