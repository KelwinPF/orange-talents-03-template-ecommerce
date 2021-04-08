package com.api.mercadolivre.util;

import com.api.mercadolivre.entity.Pergunta;

public interface EmailSender {
		public boolean send(Pergunta pergunta);
}
