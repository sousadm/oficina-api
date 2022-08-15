package com.oficina.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.dto.PessoaJuridicaDTO;
import com.oficina.model.pessoa.PessoaJuridica;

@Mapper(componentModel = "spring")
public interface PessoaJuridicaMapper {

	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateModel(PessoaJuridicaDTO dto, @MappingTarget PessoaJuridica pessoaJuridica);

	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "id", ignore = true)
	PessoaJuridica toModel(PessoaJuridicaDTO dto);

	PessoaJuridicaDTO toDto(PessoaJuridica model);

}
