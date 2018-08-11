package com.araleo02.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.araleo02.cursomc.domain.Pedido;
import com.araleo02.cursomc.repositories.PedidoRepository;
import com.araleo02.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + "Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

}
