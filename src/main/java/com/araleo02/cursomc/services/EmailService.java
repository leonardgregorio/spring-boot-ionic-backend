// aula 60. MockEmailService com Logger. Padroes Strategy e Template Method

package com.araleo02.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.araleo02.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail (Pedido obj) ;
	
	void sendEmail (SimpleMailMessage msg);
}
