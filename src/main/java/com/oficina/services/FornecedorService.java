package com.oficina.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oficina.dto.FornecedorDTO;
import com.oficina.exception.RecursoNaoLocalizadoException;
import com.oficina.mapper.FornecedorMapper;
import com.oficina.model.enums.Ativo;
import com.oficina.model.pessoa.Fornecedor;
import com.oficina.model.pessoa.Pessoa;
import com.oficina.repository.FornecedorRepository;
import com.oficina.repository.PessoaRepository;
import com.oficina.util.DateUtil;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorMapper mapper;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Transactional
	public FornecedorDTO ativar(Integer id) throws Exception {

		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new Exception("cadastro de pessoa não localizado"));

		Fornecedor fornecedor = fornecedorRepository.findByPessoa(pessoa);

		if (fornecedor == null) {
			fornecedor = new Fornecedor();
			fornecedor.setPessoa(pessoa);
			fornecedor.controle.setCadastro_dt(DateUtil.agora());
		} else {
			fornecedor.controle.setAtivo(Ativo.ATIVO);
			fornecedor.controle.setAtualizacao_dt(DateUtil.agora());
		}

		return mapper.toDto(fornecedorRepository.saveAndFlush(fornecedor));

	}

	@Transactional
	public FornecedorDTO inativar(Integer id) throws Exception {

		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new Exception("cadastro de pessoa não localizado"));

		Fornecedor fornecedor = fornecedorRepository.findByPessoa(pessoa);
		if (fornecedor == null)
			throw new Exception("fornecedor não localizado para este cadastro");

		fornecedor.controle.setAtivo(Ativo.INATIVO);
		fornecedor.controle.setAtualizacao_dt(DateUtil.agora());

		return mapper.toDto(fornecedorRepository.saveAndFlush(fornecedor));

	}
 
	@Transactional
	public FornecedorDTO salvar(@Valid FornecedorDTO dto, Integer codigo) {

		Fornecedor fornecedor = fornecedorRepository.findById(codigo)
				.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));
		mapper.updateModel(dto, fornecedor);
		fornecedor.getControle().setAtualizacao_dt(DateUtil.agora());
		return mapper.toDto(fornecedorRepository.save(fornecedor));

	}

}
