package com.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

	public List<Municipio> findByDescricaoContainingIgnoreCaseOrderByDescricao(String valor);

}
