package com.novatech.bf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/services/casier")
public class Casier {
  
	@RequestMapping(value="")
	public String home(Model model) {
		model.addAttribute("current","SERVICES");
		return "casier/casier";
	}
	
	@RequestMapping(value="/demande")
	public String demande(Model model) {
		model.addAttribute("current","SERVICES");
		return "casier/demande";
	}
	
	@RequestMapping(value="/suivre")
	public String suivre(Model model) {
		model.addAttribute("current","SERVICES");
		return "casier/demande";
	}
}
