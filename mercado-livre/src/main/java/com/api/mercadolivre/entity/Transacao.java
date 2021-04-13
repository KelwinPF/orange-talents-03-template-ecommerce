package com.api.mercadolivre.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.api.mercadolivre.util.TransacaoStatusEnum;

@Entity
@Table(name="transacoes")
public class Transacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String id_transacao;
	@CreationTimestamp
	private LocalDateTime instante;
	@ManyToOne
	@JoinColumn(nullable=false)
	private Compra compra;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TransacaoStatusEnum status;
	
	public Transacao(String id_transacao, Compra compra, TransacaoStatusEnum status) {
		super();
		this.id_transacao = id_transacao;
		this.compra = compra;
		this.status = status;
	}

	public String getId_transacao() {
		return id_transacao;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public Compra getCompra() {
		return compra;
	}

	public TransacaoStatusEnum getStatus() {
		return status;
	}
	
	
	
}
