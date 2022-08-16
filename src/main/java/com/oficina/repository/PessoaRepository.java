package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.oficina.model.pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, JpaSpecificationExecutor<Pessoa> {

	@Query(value = "from Pessoa p where p.pessoaFisica.cpf = :cpf")
	Pessoa findByCpf(String cpf);

	@Query(value = "from Pessoa p where p.pessoaJuridica.cnpj = :cnpj")
	Pessoa findByCnpj(String cnpj);

}
