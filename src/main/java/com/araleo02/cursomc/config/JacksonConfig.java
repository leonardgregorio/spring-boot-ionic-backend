// aula 50. Inserindo Pedido

//classe de configuracao com um metodo bean para registrar as subclasses
//é uma classe que tem uma informacao disponivel no inicio da aplicacao

package com.araleo02.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.araleo02.cursomc.domain.PagamentoComBoleto;
import com.araleo02.cursomc.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-
	// without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class); //subclasses que voce tem que registrar
				objectMapper.registerSubtypes(PagamentoComBoleto.class); //idem
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}
