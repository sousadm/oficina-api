package com.oficina.pessoa.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, unique = true, nullable = false)
	private String username;

	@Column(length = 255, nullable = false)
	private String senha;

	@OneToOne
	@JoinColumn(unique = true, nullable = true)
	private Pessoa pessoa;

	@Embedded
	public Controle controle;

}
