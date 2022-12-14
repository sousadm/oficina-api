package com.oficina.pessoa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@CNPJ
	@Column(length = 14, nullable = false, unique = true)
	private String cnpj;

	@Column(length = 100)
	private String fantasia;

	@Column(length = 20)
	private String IE;

	@Column(length = 20)
	private String IESub;

	@Column(length = 20)
	private String IM;

	@Column(length = 255)
	private String emailNfe;

	private Boolean isentoIe = false;

	private Boolean retensaoIss = false;

	@Embedded
	public Controle controle = new Controle();

}
