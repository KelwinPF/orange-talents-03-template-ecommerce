package com.api.mercadolivre.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.api.mercadolivre.entity.Pergunta;

@Component
@Profile("dev")
public class EmailSenderDev implements EmailSender{

	@Override
	public boolean send(Pergunta pergunta) {
		System.out.println("Caro Sr."+pergunta.getProduto().getUsuario().getLogin()+", "+
				"o usuário "+pergunta.getUsuario().getLogin()+ " fez a seguinte pergunta ao seu " +
				"produto " +pergunta.getProduto().getNome() + ": "+ pergunta.getTitulo()
				);
		return true;
	}

}
