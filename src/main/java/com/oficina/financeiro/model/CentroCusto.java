package com.oficina.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.oficina.financeiro.enums.TipoMovimento;
import com.oficina.pessoa.enums.Ativo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CentroCusto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private TipoMovimento tipo;
	
	@Enumerated(EnumType.ORDINAL)
	private Ativo ativo = Ativo.ATIVO;	

}
