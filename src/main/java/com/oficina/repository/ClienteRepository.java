package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.model.pessoa.Cliente;
import com.oficina.model.pessoa.Pessoa;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

	public Cliente findByPessoa(Pessoa pessoa);
	
}
