package com.oficina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oficina.exception.RecursoNaoLocalizadoException;
import com.oficina.model.Municipio;
import com.oficina.repository.MunicipioRepository;

@Service
public class MunicipioService {

	@Autowired
	private MunicipioRepository repository;

	public Municipio porId(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new RecursoNaoLocalizadoException("Município não localizada!"));
	}

	public List<Municipio> lista(String descricao) {
		return repository.findByDescricaoContainingIgnoreCaseOrderByDescricao(descricao);
	}

}
