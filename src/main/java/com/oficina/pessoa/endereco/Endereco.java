package com.oficina.pessoa.endereco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Municipio municipio;

	@Column(length = 100)
	private String logradouro;

	private Integer numero;

	@Column(length = 50)
	private String complemento;

	@Column(length = 50)
	private String bairro;

	@Column(length = 8)
	private String cep;

}
