package com.novatech.bf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novatech.bf.entities.Document;

public interface RepositoryDocument extends JpaRepository<Document, Long> {

}
