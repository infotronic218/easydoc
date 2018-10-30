package com.novatech.bf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.novatech.bf.dao.RepositoryRepresentant;
import com.novatech.bf.dao.RepositoryRole;
import com.novatech.bf.dao.RepositoryUser;
import com.novatech.bf.dao.RepositoryVille;
import com.novatech.bf.entities.Representant;
import com.novatech.bf.validator.CompteValidator;

@Controller
public class RegistrationController {

@Autowired
private RepositoryVille repVille;
@Autowired
private RepositoryRepresentant repUser;
@Autowired
private PasswordEncoder passwordEncoder ;
@Autowired
private RepositoryRole repRole;


	
 @RequestMapping(value="/login")	
public String login() {
 return "admin/login";

 }
 
 @RequestMapping(value="/creer_compte")
 public String nouveauCompte(Model model,
		@RequestParam(required=false) String message,
		@RequestParam(required=false)String error) {
	 if(message!=null){
		 model.addAttribute("action", "confirmation");
		 model.addAttribute("message", message);
	 }else if(error!=null) {
		 

	 }else {
		 model.addAttribute("representant", new Representant());
		 model.addAttribute("action", "creation");
		 model.addAttribute("villes", repVille.findByGrandeInstance(true));
	 }
    return "admin/compte";
 }
 
 @RequestMapping(value="/enregistrer_compte", method=RequestMethod.POST)
 public String enregistrerCompte(
		 Representant representant, @RequestParam(name="idVille")Long idVille
		 ,BindingResult result,Model model ,  RedirectAttributes ra) {
	     CompteValidator validator = new CompteValidator();
	     validator.validate(representant, result);
	     if(!result.hasErrors() && idVille!=null && repVille.existsById(idVille) && !repUser.existsById(representant.getEmail())) {
	    	 representant.setPassword(passwordEncoder.encode(representant.getPassword()));
			 representant.setVille(repVille.getOne(idVille));
			 representant.AddRole(repRole.getOne("DELEGUE"));
			 repUser.save(representant);
			 ra.addAttribute("message", "Votre compte à été entrégistré avec succès ! ");
		     return "redirect:/creer_compte";
	     }else {
	    	 if(repUser.existsById(representant.getEmail())) {
	    		 model.addAttribute("error", "Echèc d'enrégistrement de votre compte ! Ce email a déja été utilisé.");
	    	 }else {
	    		 model.addAttribute("error", "Echèc d'enrégistrement de votre compte ! Veuillez vérifier vos donnés");
	    	 }
			 model.addAttribute("representant", representant);
			 model.addAttribute("action", "creation");
			 model.addAttribute("villes", repVille.findByGrandeInstance(true));
	     }
  	   return "admin/compte";
 }

 
}
