// Aula 94. Endpoints para buscar estados e cidades

package com.araleo02.cursomc.dto;

import java.io.Serializable;

import com.araleo02.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public CidadeDTO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CidadeDTO(Cidade obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

}
