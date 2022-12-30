package com.oficina.pessoa.dto;

import java.time.LocalDateTime;

import com.oficina.pessoa.enums.TipoPessoa;

import lombok.Data;

@Data
public class PessoaDTO {

	private Integer id;
	private String nome;
	private String email;
	private String telefone;
	private String celular;
	private String ativo;
	private LocalDateTime cadastro_dt;
	private LocalDateTime atualizacao_dt;

	private Integer idVendedor;
	private Integer idCliente;
	private Integer idFornecedor;

	private Integer idMunicipio;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String municipio;

	private TipoPessoa tipoPessoa;
	private PessoaFisicaDTO fisica;
	private PessoaJuridicaDTO juridica;

}
