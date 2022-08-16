package com.oficina.model.filter;

import com.oficina.model.enums.TipoMovimento;

import lombok.Data;

@Data
public class CentroCustoFilter {

	private Integer id;
	private String descricao;
	private TipoMovimento tipo;

}
