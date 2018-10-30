package com.novatech.bf.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novatech.bf.entities.Representant;
import com.novatech.bf.entities.Role;
import com.novatech.bf.entities.User;

public interface RepositoryUser extends JpaRepository<User, String>{
	
  public List<User>findByRoles(Collection<Role> roles);
}
