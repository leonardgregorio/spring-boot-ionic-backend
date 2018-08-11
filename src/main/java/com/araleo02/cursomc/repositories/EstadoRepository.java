package com.araleo02.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.araleo02.cursomc.domain.Estado;

@Repository

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
