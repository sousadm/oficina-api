package com.oficina.pessoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.financeiro.model.CentroCusto;

public interface CentroCustoRepository
		extends JpaRepository<CentroCusto, Integer>, JpaSpecificationExecutor<CentroCusto> {

	public List<CentroCusto> findByDescricaoContainingIgnoreCaseOrderByDescricao(String valor);

}
