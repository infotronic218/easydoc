package com.novatech.bf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novatech.bf.entities.Information;

public interface RepositoryInformation extends JpaRepository<Information, Long> {

}
