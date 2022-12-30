package com.oficina.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.financeiro.model.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Integer>, JpaSpecificationExecutor<Titulo> {

}
