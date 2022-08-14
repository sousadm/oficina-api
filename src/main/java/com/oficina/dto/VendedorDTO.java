package com.oficina.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VendedorDTO {

	private Integer id;
	private Integer idPessoa;
	private String nome;
	private String cpfCnpj;
	private BigDecimal comissao;

}
