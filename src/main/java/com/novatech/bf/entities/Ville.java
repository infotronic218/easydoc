package com.novatech.bf.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Ville implements Serializable {
	private static final long serialVersionUID = 1L;
	
@Id
@GeneratedValue
private Long id;
private String name ;
private boolean grandeInstance;

public Ville() {
	
}

public Ville(String name) {
	this.name = name;
	this.grandeInstance =false;
	grandeInstance = false;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public boolean isGrandeInstance() {
	return grandeInstance;
}

public void setGrandeInstance(boolean grandeInstance) {
	this.grandeInstance = grandeInstance;
}


}
