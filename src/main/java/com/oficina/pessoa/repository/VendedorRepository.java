package com.oficina.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.pessoa.model.Pessoa;
import com.oficina.pessoa.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer>, JpaSpecificationExecutor<Vendedor> {

	public Vendedor findByPessoa(Pessoa pessoa);

}
