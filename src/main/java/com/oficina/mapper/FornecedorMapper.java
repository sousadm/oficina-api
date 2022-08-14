package com.oficina.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.dto.FornecedorDTO;
import com.oficina.model.pessoa.Fornecedor;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "pessoa", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateModel(FornecedorDTO dto, @MappingTarget Fornecedor pessoa);

	@Mapping(target = "pessoa", ignore = true)
	@Mapping(target = "controle", ignore = true)
	Fornecedor toModel(FornecedorDTO dto);

	@Mapping(target = "idPessoa", source = "pessoa.id")
	@Mapping(target = "cpfCnpj", ignore = true)
	@Mapping(target = "nome", source = "pessoa.nome")
	FornecedorDTO toDto(Fornecedor model);

}
