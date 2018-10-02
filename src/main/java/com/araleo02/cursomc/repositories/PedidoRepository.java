//aula 73. Restrição de conteúdo: cliente só recupera seus pedidos

package com.araleo02.cursomc.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.araleo02.cursomc.domain.Cliente;
import com.araleo02.cursomc.domain.Pedido;

@Repository

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
