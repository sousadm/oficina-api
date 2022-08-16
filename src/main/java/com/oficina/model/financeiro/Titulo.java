package com.oficina.model.financeiro;

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

import com.oficina.model.enums.TipoMovimento;
import com.oficina.model.pessoa.Controle;

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

	@Column(length = 100)
	private String descricao;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoMovimento tipo;

	private LocalDate vencimento_dt;
	private LocalDate previsao_dt;

	private BigDecimal valor = BigDecimal.ZERO;
	private BigDecimal saldo = BigDecimal.ZERO;

	@Embedded
	public Controle controle = new Controle();

}
