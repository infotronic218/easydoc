package com.novatech.bf.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RPT")
public class Representant extends User {
	private static final long serialVersionUID = 1L;
	private Ville ville ;

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
	

}
