package com.example.hotelmanagenetsystem.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		for(String role: roles) {
			System.out.println(role);
		}
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/room");
        }else {
        	// TODO: can update with user view start page
        	response.sendRedirect("/view/booking");
        }
	}

}
