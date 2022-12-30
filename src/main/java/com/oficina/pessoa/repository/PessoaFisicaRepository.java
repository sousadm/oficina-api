package com.oficina.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.pessoa.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {

	PessoaFisica findByCpf(String cpf);

}
