package com.oficina.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.dto.ClienteDTO;
import com.oficina.model.pessoa.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "pessoa", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateModel(ClienteDTO dto, @MappingTarget Cliente pessoa);

	@Mapping(target = "pessoa", ignore = true)
	@Mapping(target = "controle", ignore = true)
	Cliente toModel(ClienteDTO dto);

	@Mapping(target = "idPessoa", source = "pessoa.id")
	@Mapping(target = "cpfCnpj", ignore = true)
	@Mapping(target = "nome", source = "pessoa.nome")
	ClienteDTO toDto(Cliente model);

}
