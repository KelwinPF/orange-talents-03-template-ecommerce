package com.api.mercadolivre.util;

import com.api.mercadolivre.entity.Compra;

public interface Processa {
	void GerarNota(Compra compra);
	void Ranking(Compra compra);
}
