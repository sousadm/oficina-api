package com.oficina.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.oficina.financeiro.enums.TipoMovimento;
import com.oficina.financeiro.enums.TipoRepeticao;
import com.oficina.pessoa.model.Controle;
import com.oficina.pessoa.model.Pessoa;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Titulo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Titulo origem;

	@ManyToOne
	private Pessoa responsavel;

	@ManyToOne
	private CentroCusto centroCusto;

	@Size(min = 3)
	@Column(length = 100, nullable = false)
	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	private TipoMovimento tipo;

	@Enumerated(EnumType.ORDINAL)
	private TipoRepeticao repeticao = TipoRepeticao.DESABILITADO;

	private LocalDate vencimento_dt;
	private LocalDate previsao_dt;
	private LocalDate quitacao_dt;

	@PositiveOrZero(message = "valor incorreto")
	private BigDecimal valor = BigDecimal.ZERO;

	@Positive
	private BigDecimal saldo = BigDecimal.ZERO;

	@Embedded
	public Controle controle = new Controle();

}
