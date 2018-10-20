package com.novatech.bf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novatech.bf.entities.Demande;

public interface RepositoryDemande extends JpaRepository<Demande, Long>{

}
