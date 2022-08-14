package com.oficina.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Municipio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(length = 100)
	private String descricao;

	@Column(length = 3)
	private String uf;

}
