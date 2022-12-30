package com.oficina.pessoa.model;

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

import org.hibernate.validator.constraints.br.CPF;

import com.oficina.pessoa.enums.TipoSexo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PessoaFisica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@CPF
	@Column(length = 11, nullable = false, unique = true)
	private String cpf;

	@Column(length = 20)
	private String identidade;

	@Column(length = 100)
	private String pai;

	@Column(length = 100)
	private String mae;

	@Column(length = 10)
	private String orgao;

	@Column(length = 20)
	private String documento;

	@Column(length = 20)
	private String idEstrangeiro;

	@Column(length = 35)
	private String nacionalidade;

	@Column(length = 35)
	private String naturalidade;

	@Enumerated(EnumType.ORDINAL)
	private TipoSexo sexo;

	private BigDecimal altura = BigDecimal.ZERO;
	private BigDecimal peso = BigDecimal.ZERO;
	private LocalDate nascimento;
	private LocalDate emissao;

	@Embedded
	public Controle controle = new Controle();

}
