package com.oficina.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.dto.FornecedorDTO;
import com.oficina.mapper.FornecedorMapper;
import com.oficina.model.filter.PessoaFilter;
import com.oficina.model.pessoa.Fornecedor;
import com.oficina.repository.FornecedorRepository;
import com.oficina.repository.specification.FornecedorSpecification;
import com.oficina.services.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorResource {

	@Autowired
	private FornecedorMapper mapper;

	@Autowired
	private FornecedorRepository repository;

	@Autowired
	private FornecedorService service;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FornecedorDTO pessoaPorId(@PathVariable Fornecedor fornecedor) {
		return mapper.toDto(fornecedor);
	}

	@GetMapping
	public Page<FornecedorDTO> listar(Pageable paginacao, PessoaFilter filtro) {
		Specification<Fornecedor> spec = new FornecedorSpecification(filtro);
		return repository.findAll(spec, paginacao).map(x -> this.mapper.toDto(x));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<FornecedorDTO> editar(@PathVariable("id") Integer id, @Valid @RequestBody FornecedorDTO dto) {
		FornecedorDTO dtoSalvo = service.salvar(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

}
