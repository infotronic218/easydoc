package com.novatech.bf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novatech.bf.entities.Ville;

public interface RepositoryVille extends JpaRepository<Ville, Long> {
public Ville findByName(String name);
}
