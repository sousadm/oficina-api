package com.oficina.pessoa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ativo {

	INATIVO("Inativo"), 
	ATIVO("Ativo");

	private String descricao;

}
