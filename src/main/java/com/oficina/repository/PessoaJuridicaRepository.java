package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.model.pessoa.Pessoa;
import com.oficina.model.pessoa.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer> {

	public PessoaJuridica findByPessoa(Pessoa pessoa);
	
}
