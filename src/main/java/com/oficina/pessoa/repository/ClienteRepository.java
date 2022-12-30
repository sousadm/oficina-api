package com.oficina.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.pessoa.model.Cliente;
import com.oficina.pessoa.model.Pessoa;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

	public Cliente findByPessoa(Pessoa pessoa);
	
}
