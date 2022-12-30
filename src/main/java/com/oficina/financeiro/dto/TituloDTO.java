package com.oficina.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.oficina.financeiro.enums.TipoMovimento;
import com.oficina.financeiro.enums.TipoRepeticao;

import lombok.Data;

@Data
public class TituloDTO {

	private Integer id;
	private Integer idOrigem;
	private Integer idResponsavel;
	private String nomeResponsavel;
	private Integer idCentroCusto;
	private String centroDeCusto;
	private String descricao;
	private TipoMovimento tipo;
	private TipoRepeticao repeticao;
	private LocalDate vencimento_dt;
	private LocalDate previsao_dt;
	private BigDecimal valor;
	
}
