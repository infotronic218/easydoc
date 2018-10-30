package com.novatech.bf.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.novatech.bf.entities.Representant;

public class CompteValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Representant.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Representant r = (Representant) object ;
		if(!r.getPassword().equals(r.getConfirm())) {
			errors.rejectValue("password", null, "Vérifiez votre mot de passe. Assurez vous quils sont identiques");
		    return ;
		}else if(!r.getEmail().contains("@")) {
			errors.rejectValue("email", null, "Vérifiez votre adresse email");
		    return;
		}
		
	}

}
