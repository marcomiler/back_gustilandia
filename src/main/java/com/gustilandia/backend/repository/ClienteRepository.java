package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustilandia.backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
