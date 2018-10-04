//aula 75 - esqueci a senha 

package com.araleo02.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.araleo02.cursomc.domain.Cliente;
import com.araleo02.cursomc.repositories.ClienteRepository;
import com.araleo02.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	private Random rand = new Random();

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder password;

	@Autowired
	EmailService emailService;

	public void sendNewPassword(String emaal) {
		Cliente cliente = clienteRepository.findByEmail(emaal);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(password.encode(newPass));

		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();

		}
		return new String(vet);

	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {// gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else {// gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
