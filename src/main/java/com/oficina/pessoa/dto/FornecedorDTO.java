package com.oficina.pessoa.dto;

import lombok.Data;

@Data
public class FornecedorDTO {

	private Integer id;
	private Integer idPessoa;
	private String nome;
	private String cpfCnpj;

}
