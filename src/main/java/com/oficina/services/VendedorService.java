package com.oficina.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oficina.dto.VendedorDTO;
import com.oficina.exception.RecursoNaoLocalizadoException;
import com.oficina.mapper.VendedorMapper;
import com.oficina.model.enums.Ativo;
import com.oficina.model.pessoa.Pessoa;
import com.oficina.model.pessoa.Vendedor;
import com.oficina.repository.PessoaRepository;
import com.oficina.repository.VendedorRepository;
import com.oficina.util.DateUtil;

@Service
public class VendedorService {

	@Autowired
	private VendedorMapper mapper;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private VendedorRepository vendedorRepository;

	@Transactional
	public VendedorDTO ativar(Integer id) throws Exception {

		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new Exception("cadastro de pessoa não localizado"));

		Vendedor vendedor = vendedorRepository.findByPessoa(pessoa);

		if (vendedor == null) {
			vendedor = new Vendedor();
			vendedor.setPessoa(pessoa);
			vendedor.controle.setCadastro_dt(DateUtil.agora());
		} else {
			vendedor.controle.setAtivo(Ativo.ATIVO);
			vendedor.controle.setAtualizacao_dt(DateUtil.agora());
		}

		return mapper.toDto(vendedorRepository.saveAndFlush(vendedor));

	}

	@Transactional
	public VendedorDTO inativar(Integer id) throws Exception {

		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new Exception("cadastro de pessoa não localizado"));

		Vendedor vendedor = vendedorRepository.findByPessoa(pessoa);
		if (vendedor == null)
			throw new Exception("vendedor não localizado para este cadastro");

		vendedor.controle.setAtivo(Ativo.INATIVO);
		vendedor.controle.setAtualizacao_dt(DateUtil.agora());

		return mapper.toDto(vendedorRepository.saveAndFlush(vendedor));

	}
 
	@Transactional
	public VendedorDTO salvar(@Valid VendedorDTO dto, Integer codigo) {

		Vendedor vendedor = vendedorRepository.findById(codigo)
				.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));
		mapper.updateModel(dto, vendedor);
		vendedor.getControle().setAtualizacao_dt(DateUtil.agora());
		return mapper.toDto(vendedorRepository.save(vendedor));

	}

}
