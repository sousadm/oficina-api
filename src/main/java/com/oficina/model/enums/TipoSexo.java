package com.oficina.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoSexo {
	
	MASCULINO("masculino"),
	FEMININO("feminino");
	
	private String descricao;

}
