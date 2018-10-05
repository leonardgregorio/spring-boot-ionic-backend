package com.araleo02.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.araleo02.cursomc.domain.Estado;

@Repository

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Transactional(readOnly = true) // Aula 94. Endpoints para buscar estados e cidades
	public List<Estado> findAllByOrderByNome();

}
