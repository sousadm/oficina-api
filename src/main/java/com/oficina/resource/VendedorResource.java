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

import com.oficina.dto.VendedorDTO;
import com.oficina.mapper.VendedorMapper;
import com.oficina.model.filter.PessoaFilter;
import com.oficina.model.pessoa.Vendedor;
import com.oficina.repository.VendedorRepository;
import com.oficina.repository.specification.VendedorSpecification;
import com.oficina.services.VendedorService;

@RestController
@RequestMapping("/vendedor")
public class VendedorResource {

	@Autowired
	private VendedorMapper mapper;

	@Autowired
	private VendedorRepository repository;

	@Autowired
	private VendedorService service;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendedorDTO pessoaPorId(@PathVariable Vendedor id) {
		return mapper.toDto(id);
	}

	@GetMapping
	public Page<VendedorDTO> listar(Pageable paginacao, PessoaFilter filtro) {
		Specification<Vendedor> spec = new VendedorSpecification(filtro);
		return repository.findAll(spec, paginacao).map(x -> this.mapper.toDto(x));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<VendedorDTO> editar(@PathVariable("id") Integer id, @Valid @RequestBody VendedorDTO dto) {
		VendedorDTO dtoSalvo = service.salvar(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

}
