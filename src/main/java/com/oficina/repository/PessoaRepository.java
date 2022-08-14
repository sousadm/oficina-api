package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.model.pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, JpaSpecificationExecutor<Pessoa> {
	
}
