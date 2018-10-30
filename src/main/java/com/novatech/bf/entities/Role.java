package com.novatech.bf.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Length(max=20)
	private String name ;
	private String description;
	
	public Role() {
		super();
		
	}
	public Role(@Length(max = 20) String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
