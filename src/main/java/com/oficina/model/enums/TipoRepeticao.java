package com.oficina.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoRepeticao {
	
	DESABILITADO("desabilitado", 0), 
	DIARIO("diário", 1),
	SEMANAL("semanal", 7),
	QUINZENAL("quinzenal", 15),
	MENSAL("mensal", 30),
	BIMESTRAL("bimestral", 60),
	TRIMESTRAL("trimestral", 90),
	SEMESTRAL("semestral", 180),
	ANUAL("anual", 360);
	
	private String descricao;
	private int tempo;

}
