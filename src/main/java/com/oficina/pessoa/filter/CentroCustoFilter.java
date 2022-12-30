package com.oficina.pessoa.filter;

import com.oficina.financeiro.enums.TipoMovimento;
import com.oficina.pessoa.enums.Ativo;

import lombok.Data;

@Data
public class CentroCustoFilter {

	private Integer id;
	private String descricao;
	private TipoMovimento tipo;
	private Ativo ativo;

}
