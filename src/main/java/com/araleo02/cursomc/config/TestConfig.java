// aula 53. Criando o profile de teste

package com.araleo02.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.araleo02.cursomc.services.DBService;
import com.araleo02.cursomc.services.EmailService;
import com.araleo02.cursomc.services.MockEmailService;

@Configuration // ele entra nessa classe pq tem esse @, que puxa quando o profile abaixo é
				// setado, por isso as coisas funcionam
@Profile("test") // especifica o profile
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();

		return true;
	}

	@Bean // aula 60. MockEmailService com Logger. Padroes Strategy e Template Method

	public EmailService emailService() {
		return new MockEmailService();
	}
}
