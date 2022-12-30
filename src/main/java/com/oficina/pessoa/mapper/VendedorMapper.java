package com.oficina.pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.pessoa.dto.VendedorDTO;
import com.oficina.pessoa.model.Vendedor;

@Mapper(componentModel = "spring")
public interface VendedorMapper {

	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "pessoa", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateModel(VendedorDTO dto, @MappingTarget Vendedor pessoa);

	@Mapping(target = "pessoa", ignore = true)
	@Mapping(target = "controle", ignore = true)
	Vendedor toModel(VendedorDTO dto);

	@Mapping(target = "idPessoa", source = "pessoa.id")
	@Mapping(target = "cpfCnpj", ignore = true)
	@Mapping(target = "nome", source = "pessoa.nome")
	VendedorDTO toDto(Vendedor model);

}
