package com.novatech.bf.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Error404Controller implements ErrorController {

	@RequestMapping(value="/error")
	public String Error404(Model model, @RequestParam(name="message",required=false)String message) {
		if(message!=null && message!="") {
			model.addAttribute("message",message);
		}
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		
		return "/error";
	}

}
