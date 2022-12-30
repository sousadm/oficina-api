package com.oficina.pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.pessoa.dto.PessoaFisicaDTO;
import com.oficina.pessoa.model.PessoaFisica;

@Mapper(componentModel = "spring")
public interface PessoaFisicaMapper { 

	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateModel(PessoaFisicaDTO dto, @MappingTarget PessoaFisica pessoaFisica);

	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "id", ignore = true)
	PessoaFisica toModel(PessoaFisicaDTO dto);

	PessoaFisicaDTO toDto(PessoaFisica model); 

}
