package com.oficina.pessoa.filter;

import com.oficina.pessoa.enums.Ativo;

import lombok.Data;

@Data
public class PessoaFilter {

	private Integer id;
	private String nome;
	private String cpfCnpj;
	private Ativo ativo = Ativo.ATIVO;
	
	private String sortBy = "nome";
	private String sortDir = "ASC";

}
