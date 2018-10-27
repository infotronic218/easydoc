package com.novatech.bf.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADM")
public class Admin extends User{
	private static final long serialVersionUID = 1L;

}
