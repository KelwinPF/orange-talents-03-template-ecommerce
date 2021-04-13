package com.api.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.mercadolivre.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao,Long>{
	@Query(value = "Select COUNT(t.id) FROM transacoes as t "
			+ "WHERE t.compra_id = :id AND t.status = 'SUCESSO';",
			nativeQuery=true)
	Integer findAllByCompra(@Param("id") String id);


}
