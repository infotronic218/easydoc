package com.novatech.bf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

	@RequestMapping(value="/")
	public String index(Model model) {
		model.addAttribute("current","ACCEUIL");
		return "index";
	}
	
	@RequestMapping(value="/services")
	public String services(Model model) {
		model.addAttribute("current","SERVICES");
		return "services";
	}
	
	@RequestMapping(value="/apropos")
	public String apropos(Model model) {
		model.addAttribute("current","APROPOS");
		return "apropos";
	}
	
	@RequestMapping(value="/contact")
	public String contact(Model model) {
		model.addAttribute("current","CONTACT");
		return "contact";
	}
	
	
	
	
}
