package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.model.pessoa.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {

	PessoaFisica findByCpf(String cpf);

}
