package com.oficina.financeiro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.financeiro.dto.TituloDTO;
import com.oficina.financeiro.model.Titulo;

@Mapper(componentModel = "spring")
public interface TituloMapper {

	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "centroCusto.id", source = "idCentroCusto")
	@Mapping(target = "responsavel.id", source = "idResponsavel")
	@Mapping(target = "saldo", ignore = true)
	@Mapping(target = "origem", ignore = true)
	@Mapping(target = "quitacao_dt", ignore = true)
	@Mapping(target = "previsao_dt", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateModel(TituloDTO dto, @MappingTarget Titulo model);

	@Mapping(target = "centroCusto.id", source = "idCentroCusto")
	@Mapping(target = "responsavel.id", source = "idResponsavel")
	@Mapping(target = "quitacao_dt", ignore = true)
	@Mapping(target = "controle", ignore = true)
	@Mapping(target = "origem", ignore = true)
	@Mapping(target = "saldo", ignore = true)
	Titulo toModel(TituloDTO dto);

	@Mapping(target = "centroDeCusto", source = "centroCusto.descricao")
	@Mapping(target = "nomeResponsavel", source = "responsavel.nome")
	@Mapping(target = "idCentroCusto", source = "centroCusto.id")
	@Mapping(target = "idResponsavel", source = "responsavel.id")
	@Mapping(target = "idOrigem", ignore = true)
	TituloDTO toDto(Titulo model);

}
