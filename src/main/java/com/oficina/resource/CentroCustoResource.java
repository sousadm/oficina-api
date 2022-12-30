package com.oficina.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.oficina.financeiro.dto.CentroCustoDTO;
import com.oficina.financeiro.model.CentroCusto;
import com.oficina.pessoa.filter.CentroCustoFilter;
import com.oficina.pessoa.mapper.CentroCustoMapper;
import com.oficina.pessoa.services.CentroCustoService;

@RestController
@RequestMapping("/centro-custo")
public class CentroCustoResource {

	@Autowired
	private CentroCustoMapper mapper;

	@Autowired
	private CentroCustoService service;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CentroCustoDTO municipioPorId(@PathVariable CentroCusto centroCusto) {
		return mapper.toDto(centroCusto);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CentroCustoDTO> lista(CentroCustoFilter filter) {
		return service.lista(filter);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<CentroCustoDTO> criar(@Valid @RequestBody CentroCustoDTO dto) throws Exception {
		CentroCustoDTO dtoSalvo = service.salvar(dto, null);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<CentroCustoDTO> editar(@PathVariable("id") Integer id, @Valid @RequestBody CentroCustoDTO dto)
			throws Exception {
		CentroCustoDTO dtoSalvo = service.salvar(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("id") CentroCusto model) {
		service.deletar(model);
	}	
	
}
