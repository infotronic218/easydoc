package com.novatech.bf.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class ContryCodeManager {
	
	public List<Contry> getContries(){
		List<Contry> list  = new ArrayList<>();
		String countries[] = Locale.getISOCountries();
		for(String c : countries) {
			Locale l = new Locale("", c);
			list.add(new Contry(l.getDisplayCountry(), l.getCountry()));
		}
		return list;
	}

}
