package com.oficina.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.oficina.dto.CentroCustoDTO;
import com.oficina.model.financeiro.CentroCusto;

@Mapper(componentModel = "spring")
public interface CentroCustoMapper {

	@Mapping(target = "id", ignore = true)
	void updateModel(CentroCustoDTO dto, @MappingTarget CentroCusto model);
	
	CentroCusto toModel(CentroCustoDTO dto);
	
	CentroCustoDTO toDto(CentroCusto model);
	
}
