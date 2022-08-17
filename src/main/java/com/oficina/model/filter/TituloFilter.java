package com.oficina.model.filter;

import java.time.LocalDate;

import com.oficina.model.enums.TipoMovimento;
import com.oficina.model.enums.TipoRepeticao;

import lombok.Data;

@Data
public class TituloFilter {

	private Integer id;
	private Integer idOrigem;
	private Integer idResponsavel;
	private String descricao;
	private TipoMovimento tipo;
	private TipoRepeticao repeticao;
	private LocalDate vencimentoInicial;
	private LocalDate vencimentoFinal;
	private LocalDate previsaoInicial;
	private LocalDate previsaoFinal;
	private boolean encerrado = false;

}
