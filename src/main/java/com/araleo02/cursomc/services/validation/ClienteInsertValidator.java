package com.araleo02.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.araleo02.cursomc.domain.Cliente;
import com.araleo02.cursomc.domain.enums.TipoCliente;
import com.araleo02.cursomc.dto.ClienteNewDTO;
import com.araleo02.cursomc.repositories.ClienteRepository;
import com.araleo02.cursomc.resources.exception.FieldMessage;
import com.araleo02.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "Campo CPF invalido!"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSAJURIDICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "Campo CPF invalido!"));
		}		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux!=null)
		{
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}