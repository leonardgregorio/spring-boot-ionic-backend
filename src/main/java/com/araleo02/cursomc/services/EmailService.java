// aula 60. MockEmailService com Logger. Padroes Strategy e Template Method

package com.araleo02.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.araleo02.cursomc.domain.Cliente;
import com.araleo02.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail (MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass); // aula 75 - esqueci a senha
}
