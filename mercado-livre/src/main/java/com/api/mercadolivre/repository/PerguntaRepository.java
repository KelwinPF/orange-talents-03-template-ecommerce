package com.api.mercadolivre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mercadolivre.entity.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta,Long>{

	List<Pergunta> findAllByProdutoId(Long id);

}
