package com.oficina.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPessoa {
	
	INDEFINIDO("indefinido"),
	FISICA("física"), 
	JURIDICA("jurídica");
	
	private String descricao;

}
