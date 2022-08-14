package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.model.pessoa.Pessoa;
import com.oficina.model.pessoa.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {

	public PessoaFisica findByPessoa(Pessoa pessoa);
	
}
