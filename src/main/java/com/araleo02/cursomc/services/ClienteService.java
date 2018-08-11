package com.araleo02.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.araleo02.cursomc.domain.Cliente;
import com.araleo02.cursomc.repositories.ClienteRepository;
import com.araleo02.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + "Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

}
