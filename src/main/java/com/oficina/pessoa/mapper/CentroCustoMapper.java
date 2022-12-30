package com.oficina.pessoa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.financeiro.dto.CentroCustoDTO;
import com.oficina.financeiro.model.CentroCusto;

@Mapper(componentModel = "spring")
public interface CentroCustoMapper {

	@Mapping(target = "id", ignore = true)
	void updateModel(CentroCustoDTO dto, @MappingTarget CentroCusto model);
	
	CentroCusto toModel(CentroCustoDTO dto);
	
	CentroCustoDTO toDto(CentroCusto model);
	
}
