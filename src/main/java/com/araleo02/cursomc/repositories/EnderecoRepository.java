package com.araleo02.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.araleo02.cursomc.domain.Endereco;

@Repository

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
