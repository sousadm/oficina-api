package com.oficina.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oficina.dto.PessoaDTO;
import com.oficina.exception.RecursoNaoLocalizadoException;
import com.oficina.mapper.PessoaFisicaMapper;
import com.oficina.mapper.PessoaJuridicaMapper;
import com.oficina.mapper.PessoaMapper;
import com.oficina.model.enums.Ativo;
import com.oficina.model.filter.PessoaFilter;
import com.oficina.model.pessoa.Cliente;
import com.oficina.model.pessoa.Pessoa;
import com.oficina.model.pessoa.PessoaFisica;
import com.oficina.model.pessoa.PessoaJuridica;
import com.oficina.model.pessoa.Vendedor;
import com.oficina.repository.ClienteRepository;
import com.oficina.repository.MunicipioRepository;
import com.oficina.repository.PessoaFisicaRepository;
import com.oficina.repository.PessoaJuridicaRepository;
import com.oficina.repository.PessoaRepository;
import com.oficina.repository.VendedorRepository;
import com.oficina.repository.specification.PessoaSpecification;
import com.oficina.util.DateUtil;

@Service
public class PessoaService {

	@Autowired
	private PessoaMapper mapper;

	@Autowired
	private PessoaFisicaMapper pessoaFisicaMapper;

	@Autowired
	private PessoaJuridicaMapper pessoaJuridicaMapper;

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	private MunicipioRepository municipioRepository;

	public PessoaDTO porId(Integer codigo) {
		Pessoa pessoa = repository.findById(codigo)
				.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));

		PessoaDTO dto = mapper.toDto(pessoa);
		prepara(pessoa, dto);

		return dto;
	}

	private void prepara(Pessoa pessoa, PessoaDTO dto) {

		Vendedor vendedor = vendedorRepository.findByPessoa(pessoa);
		if (vendedor != null && vendedor.getControle().getAtivo() == Ativo.ATIVO)
			dto.setIdVendedor(vendedor.getId());

		Cliente cliente = clienteRepository.findByPessoa(pessoa);
		if (cliente != null && cliente.getControle().getAtivo() == Ativo.ATIVO)
			dto.setIdCliente(cliente.getId());

	}

	public Page<PessoaDTO> listar(Pageable paginacao, PessoaFilter filtro) {

		Specification<Pessoa> spec = new PessoaSpecification(filtro);

		return repository.findAll(spec, paginacao).map(x -> this.mapper.toDto(x));
	}

	public void validacaoDeDados(Pessoa pessoa, PessoaDTO dto) throws Exception {

		if (dto.getFisica() != null && dto.getJuridica() != null)
			throw new Exception("defina como pessoa jurídica ou física somente");

		if (dto.getFisica() != null && dto.getFisica().getCpf() == null)
			throw new Exception("defina o CPF para pessoa física");

		if (dto.getJuridica() != null && dto.getJuridica().getCnpj() == null)
			throw new Exception("defina o CNPJ para pessoa jurídica");

		if (dto.getIdMunicipio() != null)
			pessoa.getEndereco().setMunicipio(municipioRepository.findById(dto.getIdMunicipio())
					.orElseThrow(() -> new RecursoNaoLocalizadoException("município não localizado")));
	}

	@Transactional
	public PessoaDTO salvar(@Valid PessoaDTO dto, Integer codigo) throws Exception {

		Pessoa pessoa = mapper.toModel(dto);

		if (codigo != null) {
			pessoa = repository.findById(codigo)
					.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));
			mapper.updateModel(dto, pessoa);
		} else {
			pessoa.controle.setCadastro_dt(DateUtil.agora());
		}

		validacaoDeDados(pessoa, dto);

		pessoa = repository.saveAndFlush(pessoa);

		if (dto.getFisica() != null) {
			PessoaFisica fisica = pessoaFisicaRepository.findByPessoa(pessoa);
			if (fisica == null)
				fisica = pessoaFisicaMapper.toModel(dto.getFisica());
			else
				pessoaFisicaMapper.updateModel(dto.getFisica(), fisica);
			fisica.setPessoa(pessoa);
			pessoaFisicaRepository.saveAndFlush(fisica);
		}

		if (dto.getJuridica() != null) {
			PessoaJuridica juridica = pessoaJuridicaRepository.findByPessoa(pessoa);
			pessoaJuridicaMapper.updateModel(dto.getJuridica(), juridica);
			juridica.setPessoa(pessoa);
			pessoaJuridicaRepository.saveAndFlush(juridica);
		}

		return mapper.toDto(pessoa);

	}

	@Transactional
	public PessoaDTO ativar(Integer codigo) throws Exception {

		Pessoa pessoa = repository.findById(codigo)
				.orElseThrow(() -> new Exception("cadastro de pessoa não localizado"));

		pessoa.controle.setAtualizacao_dt(DateUtil.agora());
		pessoa.controle.setAtivo(Ativo.ATIVO);

		return mapper.toDto(repository.saveAndFlush(pessoa));

	}

	@Transactional
	public PessoaDTO inativar(Integer codigo) throws Exception {

		Pessoa pessoa = repository.findById(codigo)
				.orElseThrow(() -> new Exception("cadastro de pessoa não localizado"));

		pessoa.controle.setAtualizacao_dt(DateUtil.agora());
		pessoa.controle.setAtivo(Ativo.INATIVO);

		return mapper.toDto(repository.saveAndFlush(pessoa));

	}

}
