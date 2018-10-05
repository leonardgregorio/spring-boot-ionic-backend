// Aula 94. Endpoints para buscar estados e cidades

package com.araleo02.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.araleo02.cursomc.domain.Cidade;
import com.araleo02.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> FindbyEstado(Integer estadoId){
		return cidadeRepository.findCidades(estadoId);
	}
}
