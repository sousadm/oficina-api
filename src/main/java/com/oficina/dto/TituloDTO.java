package com.oficina.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.oficina.model.enums.TipoMovimento;
import com.oficina.model.enums.TipoRepeticao;

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
