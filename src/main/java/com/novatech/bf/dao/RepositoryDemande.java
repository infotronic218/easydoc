package com.novatech.bf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novatech.bf.entities.Demande;

public interface RepositoryDemande extends JpaRepository<Demande, Long>{
	//@Query("SELECT d FROM Demande d WHERE d.email= :email AND d.password= :password")
	public List<Demande> findByEmail(String email);
	@Query("SELECT d FROM Demande d WHERE d.email= :email AND d.password= :password")
	public List<Demande> trouver(@Param("email")String email, @Param("password")String password);
    
}
