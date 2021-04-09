package com.api.mercadolivre.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.api.mercadolivre.entity.Pergunta;

@Component
@Profile("dev")
public class EmailSenderDev implements EmailSender{

	@Override
	public boolean send(String mensagem) {
		System.out.println(mensagem);
		return true;
	}

}
