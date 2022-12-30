package com.oficina.pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.pessoa.dto.PessoaJuridicaDTO;
import com.oficina.pessoa.model.PessoaJuridica;

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
