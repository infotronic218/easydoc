package com.novatech.bf.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Document implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String typePiece;
	private String numero ;
	@Lob
	private byte[] fichier;
	
	@Transient
	private MultipartFile file;
	public Document() {
		
	}
	public String getTypePiece() {
		return typePiece;
	}
	public void setTypePiece(String typePiece) {
		this.typePiece = typePiece;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getFichier() {
		return fichier;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public void setFichier(byte[] fichier) {
		this.fichier = fichier;
	}
	

}
