// Aula 94. Endpoints para buscar estados e cidades

package com.araleo02.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.araleo02.cursomc.domain.Estado;
import com.araleo02.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {

	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> findAll(){
		return estadoRepository.findAllByOrderByNome();
	}
}
