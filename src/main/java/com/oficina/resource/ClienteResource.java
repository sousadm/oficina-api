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

import com.oficina.dto.ClienteDTO;
import com.oficina.mapper.ClienteMapper;
import com.oficina.model.filter.PessoaFilter;
import com.oficina.model.pessoa.Cliente;
import com.oficina.repository.ClienteRepository;
import com.oficina.repository.specification.ClienteSpecification;
import com.oficina.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteMapper mapper;

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ClienteService service;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteDTO pessoaPorId(@PathVariable Cliente id) {
		return mapper.toDto(id);
	}

	@GetMapping
	public Page<ClienteDTO> listar(Pageable paginacao, PessoaFilter filtro) {
		Specification<Cliente> spec = new ClienteSpecification(filtro);
		return repository.findAll(spec, paginacao).map(x -> this.mapper.toDto(x));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ClienteDTO> editar(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDTO dto) {
		ClienteDTO dtoSalvo = service.salvar(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

}
