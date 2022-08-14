package com.oficina.model.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

	@OneToOne
	private Pessoa pessoa;

	@Column(length = 100)
	private String fantasia;

	@CNPJ
	@Column(length = 14)
	private String cnpj;

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
