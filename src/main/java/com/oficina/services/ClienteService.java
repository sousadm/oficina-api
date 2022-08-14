package com.oficina.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oficina.dto.ClienteDTO;
import com.oficina.exception.RecursoNaoLocalizadoException;
import com.oficina.mapper.ClienteMapper;
import com.oficina.model.enums.Ativo;
import com.oficina.model.pessoa.Cliente;
import com.oficina.model.pessoa.Pessoa;
import com.oficina.repository.ClienteRepository;
import com.oficina.repository.PessoaRepository;
import com.oficina.util.DateUtil;

@Service
public class ClienteService {

	@Autowired
	private ClienteMapper mapper;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public ClienteDTO ativar(Integer id) throws Exception {

		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new Exception("cadastro de pessoa não localizado"));

		Cliente cliente = clienteRepository.findByPessoa(pessoa);

		if (cliente == null) {
			cliente = new Cliente();
			cliente.setPessoa(pessoa);
			cliente.controle.setCadastro_dt(DateUtil.agora());
		} else {
			cliente.controle.setAtivo(Ativo.ATIVO);
			cliente.controle.setAtualizacao_dt(DateUtil.agora());
		}

		return mapper.toDto(clienteRepository.saveAndFlush(cliente));

	}

	@Transactional
	public ClienteDTO inativar(Integer id) throws Exception {

		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new Exception("cadastro de pessoa não localizado"));

		Cliente cliente = clienteRepository.findByPessoa(pessoa);
		if (cliente == null)
			throw new Exception("cliente não localizado para este cadastro");

		cliente.controle.setAtivo(Ativo.INATIVO);
		cliente.controle.setAtualizacao_dt(DateUtil.agora());

		return mapper.toDto(clienteRepository.saveAndFlush(cliente));

	}
 
	@Transactional
	public ClienteDTO salvar(@Valid ClienteDTO dto, Integer codigo) {

		Cliente cliente = clienteRepository.findById(codigo)
				.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));
		mapper.updateModel(dto, cliente);
		cliente.getControle().setAtualizacao_dt(DateUtil.agora());
		return mapper.toDto(clienteRepository.save(cliente));

	}

}
