package com.api.mercadolivre.entity;

import java.math.BigDecimal;
import java.util.Map;

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

import com.api.mercadolivre.util.GatewayEnum;
import com.api.mercadolivre.repository.CompraRepository;
import com.api.mercadolivre.util.CompraStatusEnum;

@Entity
@Table(name="compras")
public class Compra {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
    @Enumerated(EnumType.STRING)
	private GatewayEnum gateway;
	@ManyToOne
	@JoinColumn(nullable=false)
	private Produto produto;
	@Column(nullable=false)
	private Integer quantidade;
	@ManyToOne
	@JoinColumn(nullable=false)
	private Usuario usuario;
	@Column(nullable = false,scale = 2)
	private BigDecimal valor_unitario;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private CompraStatusEnum status = CompraStatusEnum.INICIADA;
	
	public Compra(GatewayEnum gateway, Produto produto, Integer quantidade, 
			Usuario usuario, BigDecimal valor_unitario) {
		super();
		this.gateway = gateway;
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.valor_unitario = valor_unitario;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public BigDecimal getValor_unitario() {
		return valor_unitario;
	}

	public CompraStatusEnum getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

	public GatewayEnum getGateway() {
		return gateway;
	}

	public void setGateway(GatewayEnum gateway) {
		this.gateway = gateway;
	}
	
	@Deprecated
	public Compra() {
		
	}

	public Compra finalizacompra(CompraRepository repo) {
		this.status = CompraStatusEnum.CONCLUIDA;
		return repo.save(this);
	}
	
	
}
