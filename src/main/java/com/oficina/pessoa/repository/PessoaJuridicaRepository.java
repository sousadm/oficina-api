package com.oficina.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.pessoa.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer> {

	public PessoaJuridica findByCnpj(String cnpj);

}
