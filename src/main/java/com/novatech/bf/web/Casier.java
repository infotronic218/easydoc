package com.novatech.bf.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.novatech.bf.services.ContryCodeManager;

@Controller
@RequestMapping(value="/services/casier")
public class Casier {
	@Autowired
	private ContryCodeManager codeManager;
	
	@RequestMapping(value="")
	public String home(Model model) {
		model.addAttribute("current","SERVICES");
		return "casier/casier";
	}
	
	@RequestMapping(value="/demande")
	public String demande(Model model) {
		model.addAttribute("current","SERVICES");
		model.addAttribute("etape",1);
		model.addAttribute("countries", codeManager.getContries());
		return "casier/demande";
	}
	
	@RequestMapping(value="/demande/documents", method=RequestMethod.POST)
	public String demandeDocuemts(Model model) {
		model.addAttribute("current","SERVICES");
		model.addAttribute("countries", codeManager.getContries());
		model.addAttribute("etape",2);
		return "casier/demande";
	}
	
	@RequestMapping(value="/demande/confirmation")
	public String demandeConfirmation(Model model) {
		model.addAttribute("current","SERVICES");
		model.addAttribute("etape",3);
		model.addAttribute("countries", codeManager.getContries());
		return "casier/demande";
	}
	
	@RequestMapping(value="/demande/save")
	public String demandeSave(Model model) {
		model.addAttribute("current","SERVICES");
		model.addAttribute("etape",4);
		model.addAttribute("countries", codeManager.getContries());
		return "casier/demande";
	}
	
	@RequestMapping(value="/suivre")
	public String suivre(Model model) {
		model.addAttribute("current","SERVICES");
		return "casier/demande";
	}
}
