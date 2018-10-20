package com.novatech.bf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novatech.bf.entities.Type;

public interface RepositoryType extends JpaRepository<Type, String> {
  public boolean existsByName(String name);
  public Type findByName(String name);
}
