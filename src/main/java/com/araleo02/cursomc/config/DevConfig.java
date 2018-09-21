// aula 54. Criando o profile de dev

package com.araleo02.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.araleo02.cursomc.services.DBService;

@Configuration // ele entra nessa classe pq tem esse @, que puxa quando o profile abaixo é
				// setado, por isso as coisas funcionam
@Profile("dev") // especifica o profile
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}") // aula 54
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		} else {

			dbService.instantiateTestDatabase();

			return true;
		}

	}
}
