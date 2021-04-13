package com.api.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.api.mercadolivre.configuration.ExistsSuccess;
import com.api.mercadolivre.entity.Compra;
import com.api.mercadolivre.entity.Transacao;
import com.api.mercadolivre.util.TransacaoStatusEnum;
@ExistsSuccess
public class TransacaoDTO {
	@NotBlank(message="mensagem")
	private String id_transacao;
	@NotBlank
    @Pattern(regexp="^(SUCESSO|ERRO|1|0)$")
	private String status;
	
	public TransacaoDTO(@NotBlank String id_transacao, @NotBlank String status) {
		super();
		this.id_transacao = id_transacao;
		this.status = status;
	}
	
	public String getId_transacao() {
		return id_transacao;
	}

	public String getStatus() {
		return status;
	}

	public Transacao convert(Compra compra) {
		return new Transacao(id_transacao, compra, TransacaoStatusEnum.getEnum(status));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransacaoDTO other = (TransacaoDTO) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
}
