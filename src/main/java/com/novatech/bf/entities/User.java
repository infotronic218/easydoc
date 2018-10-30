package com.novatech.bf.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
@Entity
@Table(name="tusers")
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Length(max=50)
	private String email;
	private String password;
	private boolean active ;
	private String nom;
	private String prenom;
	
	@OneToMany
	private Collection<Role> roles ;
	public String getEmail() {
		
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	@Transient
	private String confirm;
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	public void AddRole(Role role) {
		if(this.roles==null) {
			this.roles = new ArrayList<>();
		}
		roles.add(role);
	}

}
