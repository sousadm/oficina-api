package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.model.pessoa.Pessoa;
import com.oficina.model.pessoa.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer>, JpaSpecificationExecutor<Vendedor> {

	public Vendedor findByPessoa(Pessoa pessoa);

}
