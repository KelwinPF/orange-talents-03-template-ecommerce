package com.api.mercadolivre.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.mercadolivre.configuration.security.util.JwtTokenUtil;

public class AutenticacaoTokenFilter extends OncePerRequestFilter{
	
    private JwtTokenUtil jwtTokenUtil;
    
    @Qualifier("AutenticacaoService")
    private UserDetailsService service;
    
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    
    public AutenticacaoTokenFilter(JwtTokenUtil jwtTokenUtil, UserDetailsService service) {
		super();
		this.jwtTokenUtil = jwtTokenUtil;
		this.service = service;
	}



	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(AUTH_HEADER);
        
        if (token != null && token.startsWith(BEARER_PREFIX)) {
            token = token.substring(7);
        }
        
        String username = jwtTokenUtil.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.service.loadUserByUsername(username);

            if (jwtTokenUtil.validToken(token)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }


}
