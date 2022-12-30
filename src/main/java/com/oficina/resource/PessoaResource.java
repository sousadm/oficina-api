package com.oficina.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.pessoa.dto.ClienteDTO;
import com.oficina.pessoa.dto.FornecedorDTO;
import com.oficina.pessoa.dto.PessoaDTO;
import com.oficina.pessoa.dto.VendedorDTO;
import com.oficina.pessoa.filter.PessoaFilter;
import com.oficina.pessoa.services.ClienteService;
import com.oficina.pessoa.services.FornecedorService;
import com.oficina.pessoa.services.PessoaService;
import com.oficina.pessoa.services.VendedorService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private VendedorService vendedorService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private FornecedorService fornecedorService;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PessoaDTO pessoaPorId(@PathVariable Integer id) {
		return pessoaService.dtoPorId(id);
	}

	@GetMapping
	public ResponseEntity<Page<PessoaDTO>> listar(Pageable paginacao, PessoaFilter filtro) {
		Page<PessoaDTO> fornecedores = pessoaService.listar(paginacao, filtro);
		HttpStatus statusResponse = (fornecedores.getTotalPages() > 1) ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK;
		return ResponseEntity.status(statusResponse).body(fornecedores);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<PessoaDTO> criar(@Valid @RequestBody PessoaDTO dto) throws Exception {
		PessoaDTO dtoSalvo = this.pessoaService.salvar(dto, null);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<PessoaDTO> editar(@PathVariable("id") Integer id, @Valid @RequestBody PessoaDTO dto)
			throws Exception {
		PessoaDTO dtoSalvo = this.pessoaService.salvar(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
	}

	@PutMapping("/{id}/ativar")
	public ResponseEntity<PessoaDTO> ativar(@PathVariable("id") Integer codigo) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.ativar(codigo));
	}

	@PutMapping("/{id}/inativar")
	public ResponseEntity<PessoaDTO> inativar(@PathVariable("id") Integer codigo) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.inativar(codigo));
	}

	@PutMapping("/{id}/ativar-cliente")
	public ResponseEntity<ClienteDTO> ativarCliente(@PathVariable("id") Integer pessoa) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.ativar(pessoa));
	}

	@PutMapping("/{id}/ativar-vendedor")
	public ResponseEntity<VendedorDTO> ativarVendedor(@PathVariable("id") Integer pessoa) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(vendedorService.ativar(pessoa));
	}

	@PutMapping("/{id}/ativar-fornecedor")
	public ResponseEntity<FornecedorDTO> ativarFornecedor(@PathVariable("id") Integer pessoa) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.ativar(pessoa));
	}

	@PutMapping("/{id}/inativar-cliente")
	public ResponseEntity<ClienteDTO> inativarCliente(@PathVariable("id") Integer pessoa) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.inativar(pessoa));
	}

	@PutMapping("/{id}/inativar-vendedor")
	public ResponseEntity<VendedorDTO> inativarVendedor(@PathVariable("id") Integer pessoa) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(vendedorService.inativar(pessoa));
	}

	@PutMapping("/{id}/inativar-fornecedor")
	public ResponseEntity<FornecedorDTO> inativarFornecedor(@PathVariable("id") Integer pessoa) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.inativar(pessoa));
	}

}
