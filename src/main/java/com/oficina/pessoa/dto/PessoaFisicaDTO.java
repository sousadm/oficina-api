package com.oficina.pessoa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.oficina.pessoa.enums.TipoSexo;

import lombok.Data;

@Data
public class PessoaFisicaDTO {

	private String cpf;
	private String identidade;
	private String pai;
	private String mae;
	private String orgao;
	private String documento;
	private String idEstrangeiro;
	private String nacionalidade;
	private String naturalidade;
	private TipoSexo sexo;
	private BigDecimal altura = BigDecimal.ZERO;
	private BigDecimal peso = BigDecimal.ZERO;
	private LocalDate nascimento;
	private LocalDate emissao;

}
