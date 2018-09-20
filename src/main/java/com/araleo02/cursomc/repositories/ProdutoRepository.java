// 47. Busca de pedidos por nome e categorias - PARTE 1

package com.araleo02.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.araleo02.cursomc.domain.Categoria;
import com.araleo02.cursomc.domain.Produto;

@Repository

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	//quando faz um metodo customizado, se passa a consulta pjql acima.
	//Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias , Pageable pageRequest);
	
	//se montar assim como abaixo, usando padrao de nomes, a query é feita automaticamente
	Page<Produto>findDistincBynomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias , Pageable pageRequest);
	
}
