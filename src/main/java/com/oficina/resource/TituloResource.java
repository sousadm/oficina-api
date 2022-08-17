package com.oficina.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.dto.TituloDTO;
import com.oficina.mapper.TituloMapper;
import com.oficina.model.filter.TituloFilter;
import com.oficina.model.financeiro.Titulo;
import com.oficina.services.TituloService;

@RestController
@RequestMapping("/titulo")
public class TituloResource {

	@Autowired
	private TituloMapper mapper;

	@Autowired
	private TituloService service;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TituloDTO porId(@PathVariable Integer id) {
		return mapper.toDto(service.porId(id));
	}

	@GetMapping
	public ResponseEntity<Page<TituloDTO>> listar(Pageable paginacao, TituloFilter filtro) {
		Page<TituloDTO> lista = service.listar(paginacao, filtro);
		HttpStatus statusResponse = (lista.getTotalPages() > 1) ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK;
		return ResponseEntity.status(statusResponse).body(lista);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<TituloDTO> criar(@Valid @RequestBody TituloDTO dto) throws Exception {
		TituloDTO dtoSalvo = service.salvar(dto, null);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<TituloDTO> editar(@PathVariable("id") Integer id, @Valid @RequestBody TituloDTO dto)
			throws Exception {
		TituloDTO dtoSalvo = service.salvar(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("id") Titulo model) {
		service.deletar(model);
	}

}
