package com.beltsoft.jim.fer.seguridad;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class FerAuthenticationProvider  implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
        return authentication;
	}
	
	@Override
	public boolean supports(Class<? extends Object> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
