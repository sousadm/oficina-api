package com.oficina.pessoa.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClienteDTO {

	private Integer id;
	private Integer idPessoa;
	private String nome;
	private String cpfCnpj;
	private BigDecimal limiteCredito = BigDecimal.ZERO;
	
}
