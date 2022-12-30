package com.oficina.pessoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.pessoa.endereco.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

	public List<Municipio> findByDescricaoContainingIgnoreCaseOrderByDescricao(String valor);

}
