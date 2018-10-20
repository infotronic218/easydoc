package com.novatech.bf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Controller
public class ExeptionHandler {

	@ExceptionHandler(value= {NoHandlerFoundException.class})
	public String HandlerNotFind(Model model) {
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String ErrorHandler (Model model, Exception e) {
		
		return "error";
				
	}
}
