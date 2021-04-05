package com.api.mercadolivre.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.mercadolivre.dto.UsuarioDTO;
import com.api.mercadolivre.entity.Usuario;
import com.api.mercadolivre.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerTest {
	
	Usuario user_1;
	
	@Mock
	UsuarioRepository usuario_repository;
	
    @Autowired
    MockMvc mvc;
    
	@BeforeEach
	public void SetUp() {
		user_1 = new Usuario("teste@email.com","123456");
	}
	
	@Test
	public void testSave() throws Exception{
		BDDMockito.given(usuario_repository.save(Mockito.any(Usuario.class))).willReturn(user_1);
		mvc.perform(MockMvcRequestBuilders.post("/usuarios")
                .content(getJsonPayload("teste@email.com","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.login").value("teste@email.com"))
		        .andExpect(jsonPath("$.data.senha").value("123456"));
	}
	
    public String getJsonPayload(String Login,String password) throws JsonProcessingException {
        UsuarioDTO dto = new UsuarioDTO(Login,password);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

}
