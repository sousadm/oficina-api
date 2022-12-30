package com.oficina.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.pessoa.endereco.Municipio;
import com.oficina.pessoa.services.MunicipioService;

@RestController
@RequestMapping("/municipio")
public class MunicipioResource {

	@Autowired
	private MunicipioService service;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Municipio municipioPorId(@PathVariable Integer id) {
		return service.porId(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Municipio> lista(String descricao) {
		return service.lista(descricao);
	}	

}
