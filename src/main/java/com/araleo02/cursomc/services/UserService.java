// aula 72. Restrição de conteúdo: cliente só recupera ele mesmo

package com.araleo02.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.araleo02.cursomc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
