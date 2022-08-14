package com.oficina.model.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.oficina.model.Endereco;
import com.oficina.model.enums.TipoPessoa;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Pessoa pessoa;

	@Column(length = 100)
	private String nome;

	@Column(length = 255)
	private String email;

	@Column(length = 20)
	private String telefone;

	@Column(length = 20)
	private String celular;

	@Embedded
	public Controle controle = new Controle();

	@Embedded
	private Endereco endereco;

	@Version
	private Integer versao;

	@Enumerated(EnumType.ORDINAL)
	private TipoPessoa tipoPessoa = TipoPessoa.INDEFINIDO;

}
