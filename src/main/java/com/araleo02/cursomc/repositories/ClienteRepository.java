package com.araleo02.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.araleo02.cursomc.domain.Cliente;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
