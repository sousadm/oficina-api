package com.oficina.financeiro.dto;

import com.oficina.financeiro.enums.TipoMovimento;
import com.oficina.pessoa.enums.Ativo;

import lombok.Data;

@Data
public class CentroCustoDTO {

	private Integer id;
	private String descricao;
	private TipoMovimento tipo;
	private Ativo ativo = Ativo.ATIVO;

}
