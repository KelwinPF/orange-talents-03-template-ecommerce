package com.api.mercadolivre.configuration.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercadolivre.configuration.security.dto.JwtAuthenticationDTO;
import com.api.mercadolivre.configuration.security.dto.TokenDTO;
import com.api.mercadolivre.configuration.security.util.JwtTokenUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    @Qualifier("AutenticacaoService")
    private UserDetailsService service;
    
    public AutenticacaoController(JwtTokenUtil jwtTokenUtil,UserDetailsService service,AuthenticationManager authenticationManager) {
		this.service = service;
		this.jwtTokenUtil = jwtTokenUtil;
		this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> gerarTokenJwt(@RequestBody @Valid JwtAuthenticationDTO authenticationDto, BindingResult result)
            throws AuthenticationException {
    			try {
    		        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
    		                authenticationDto.getLogin(), authenticationDto.getSenha()));
    		        SecurityContextHolder.getContext().setAuthentication(authentication);
    		
    		        UserDetails userDetails = service.loadUserByUsername(authenticationDto.getLogin());
    		        String token = jwtTokenUtil.getToken(userDetails);
    				
    		        return ResponseEntity.ok(new TokenDTO(token));
    			}catch(AuthenticationException e){
    				return ResponseEntity.badRequest().build();
    			}
		
    		}
            
            
    }
