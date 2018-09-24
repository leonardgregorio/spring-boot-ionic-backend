// aula 60. MockEmailService com Logger. Padroes Strategy e Template Method

package com.araleo02.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de emial....");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}
}
