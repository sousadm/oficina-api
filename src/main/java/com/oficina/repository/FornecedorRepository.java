package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.model.pessoa.Fornecedor;
import com.oficina.model.pessoa.Pessoa;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>, JpaSpecificationExecutor<Fornecedor> {

	public Fornecedor findByPessoa(Pessoa pessoa);
}
