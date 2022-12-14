package com.oficina.pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.pessoa.dto.PessoaDTO;
import com.oficina.pessoa.model.Pessoa;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

	@Mapping(target = "pessoaFisica", ignore = true)
	@Mapping(target = "pessoaJuridica", ignore = true)
	@Mapping(target = "endereco.bairro", source = "bairro")
	@Mapping(target = "endereco.cep", source = "cep")
	@Mapping(target = "endereco.complemento", source = "complemento")
	@Mapping(target = "endereco.municipio.id", source = "idMunicipio")
	@Mapping(target = "endereco.logradouro", source = "logradouro")
	@Mapping(target = "endereco.numero", source = "numero")
	@Mapping(target = "controle.atualizacao_dt", defaultExpression = "java(LocalDateTime.now(java.time.ZoneId.systemDefault()))")
	@Mapping(target = "endereco.municipio", ignore = true)
	@Mapping(target = "pessoa", ignore = true)
	@Mapping(target = "versao", ignore = true)
	@Mapping(target = "id", ignore = true) // obrigatório
	void updateModel(PessoaDTO dto, @MappingTarget Pessoa pessoa);

	@Mapping(target = "pessoaFisica", ignore = true)
	@Mapping(target = "pessoaJuridica", ignore = true)
	@Mapping(target = "endereco.bairro", source = "bairro")
	@Mapping(target = "endereco.cep", source = "cep")
	@Mapping(target = "endereco.complemento", source = "complemento")
	@Mapping(target = "endereco.logradouro", source = "logradouro")
	@Mapping(target = "endereco.numero", source = "numero")
	@Mapping(target = "endereco.municipio", ignore = true)
	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "pessoa", ignore = true)
	@Mapping(target = "versao", ignore = true)
	Pessoa toModel(PessoaDTO dto);

	@Mapping(target = "fisica", source = "pessoaFisica")
	@Mapping(target = "juridica", source = "pessoaJuridica")
	@Mapping(target = "idCliente", ignore = true)
	@Mapping(target = "idFornecedor", ignore = true)
	@Mapping(target = "idVendedor", ignore = true)
	@Mapping(target = "municipio", source = "endereco.municipio.descricao")
	@Mapping(target = "bairro", source = "endereco.bairro")
	@Mapping(target = "cep", source = "endereco.cep")
	@Mapping(target = "complemento", source = "endereco.complemento")
	@Mapping(target = "idMunicipio", source = "endereco.municipio.id")
	@Mapping(target = "logradouro", source = "endereco.logradouro")
	@Mapping(target = "numero", source = "endereco.numero")
	@Mapping(target = "ativo", source = "controle.ativo")
	@Mapping(target = "cadastro_dt", source = "controle.cadastro_dt")
	@Mapping(target = "atualizacao_dt", source = "controle.atualizacao_dt")
	PessoaDTO toDto(Pessoa model);

}
