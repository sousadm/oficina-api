package com.oficina.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.pessoa.model.Fornecedor;
import com.oficina.pessoa.model.Pessoa;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>, JpaSpecificationExecutor<Fornecedor> {

	public Fornecedor findByPessoa(Pessoa pessoa);
}
