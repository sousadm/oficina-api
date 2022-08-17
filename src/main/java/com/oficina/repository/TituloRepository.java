package com.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oficina.model.financeiro.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Integer>, JpaSpecificationExecutor<Titulo> {

}
