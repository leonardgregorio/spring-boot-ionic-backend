package com.araleo02.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.araleo02.cursomc.domain.Cliente;
import com.araleo02.cursomc.dto.ClienteDTO;
import com.araleo02.cursomc.dto.ClienteNewDTO;
import com.araleo02.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	//Aula 93. Endpoint para buscar cliente por email
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@RequestParam (value="value") String email) {
		Cliente obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	
	// metodo put para atualizar um id
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	// metodo delete para deletar um id
	@PreAuthorize("hasAnyRole('ADMIN')") // aula 71. Autorizando endpoints para perfis específicos
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// metodo para retornar todas OS CLIENTES
	@PreAuthorize("hasAnyRole('ADMIN')") // aula 71. Autorizando endpoints para perfis específicos
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	// metodo para retornar uma paginacao de todas os clientes
	// isso facilita depois para montar o front end para fazer os retornos por get
	// atraves de paginas com oas parametros abaixo
	@PreAuthorize("hasAnyRole('ADMIN')") // aula 71. Autorizando endpoints para perfis específicos
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
		Page<Cliente> list = service.findPage(page, linesPerPage, direction, orderBy);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@RequestMapping(value="/picture", method = RequestMethod.POST) 	// Aula 83. Enviando imagem via endpoint
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}

}
