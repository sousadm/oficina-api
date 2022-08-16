package com.oficina.dto;

import com.oficina.model.enums.TipoMovimento;

import lombok.Data;

@Data
public class CentroCustoDTO {

	private Integer id;
	private String descricao;
	private TipoMovimento tipo;

}