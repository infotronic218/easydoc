package com.novatech.bf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.novatech.bf.dao.RepositoryRole;
import com.novatech.bf.entities.Role;

@SpringBootApplication
public class EasydocApplication {
	
    
	public static void main(String[] args) {
	  ApplicationContext ctx= SpringApplication.run(EasydocApplication.class, args);
	  
	  RepositoryRole repRole = ctx.getBean(RepositoryRole.class);
	  if(!repRole.existsById("REPRESENTANT")) {
		  Role role = new Role("DELEGUE","Autorisation pour les administrateur déléqués");
		  repRole.save(role);
	  }
	}
}
