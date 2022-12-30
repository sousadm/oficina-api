package com.oficina.pessoa.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oficina.infra.exception.RecursoNaoLocalizadoException;
import com.oficina.infra.util.DateUtil;
import com.oficina.pessoa.dto.PessoaDTO;
import com.oficina.pessoa.enums.Ativo;
import com.oficina.pessoa.enums.TipoPessoa;
import com.oficina.pessoa.filter.PessoaFilter;
import com.oficina.pessoa.mapper.PessoaFisicaMapper;
import com.oficina.pessoa.mapper.PessoaJuridicaMapper;
import com.oficina.pessoa.mapper.PessoaMapper;
import com.oficina.pessoa.model.Cliente;
import com.oficina.pessoa.model.Fornecedor;
import com.oficina.pessoa.model.Pessoa;
import com.oficina.pessoa.model.PessoaFisica;
import com.oficina.pessoa.model.PessoaJuridica;
import com.oficina.pessoa.model.Vendedor;
import com.oficina.pessoa.repository.ClienteRepository;
import com.oficina.pessoa.repository.FornecedorRepository;
import com.oficina.pessoa.repository.MunicipioRepository;
import com.oficina.pessoa.repository.PessoaFisicaRepository;
import com.oficina.pessoa.repository.PessoaJuridicaRepository;
import com.oficina.pessoa.repository.PessoaRepository;
import com.oficina.pessoa.repository.VendedorRepository;
import com.oficina.pessoa.repository.specification.PessoaSpecification;

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
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	private MunicipioRepository municipioRepository;

	public Pessoa porid(Integer codigo) {
		return repository.findById(codigo)
				.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));
	}

	public PessoaDTO dtoPorId(Integer codigo) {
		Pessoa pessoa = this.porid(codigo);
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

		Fornecedor fornecedor = fornecedorRepository.findByPessoa(pessoa);
		if (fornecedor != null && fornecedor.getControle().getAtivo() == Ativo.ATIVO)
			dto.setIdFornecedor(fornecedor.getId());

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

		preparaPessoaFisica(pessoa, dto);

		preparaPessoaJuridica(pessoa, dto);

		if (pessoa.getPessoaFisica() == null && pessoa.getPessoaJuridica() == null) {
			pessoa.setTipoPessoa(TipoPessoa.INDEFINIDO);
		}
		pessoa = repository.saveAndFlush(pessoa);

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

	private void preparaPessoaFisica(Pessoa pessoa, @Valid PessoaDTO dto) throws Exception {

		if (dto.getFisica() != null) {

			if (dto.getFisica().getCpf() == null)
				throw new Exception("CPF obrigatório");

			Pessoa pesquisa = repository.findByCpf(dto.getFisica().getCpf());
			if (pesquisa != null && !pesquisa.getId().equals(pessoa.getId()))
				throw new Exception("CPF já cadastrado para outra pessoa");

			PessoaFisica fisica = pesquisa != null ? pesquisa.getPessoaFisica() : new PessoaFisica();
			if (fisica == null) {
				fisica = pessoaFisicaMapper.toModel(dto.getFisica());
				fisica.controle.setCadastro_dt(DateUtil.agora());
			} else {
				pessoaFisicaMapper.updateModel(dto.getFisica(), fisica);
				fisica.controle.setAtualizacao_dt(DateUtil.agora());
			}

			fisica = pessoaFisicaRepository.saveAndFlush(fisica);
			pessoa.setPessoaFisica(fisica);
			pessoa.setTipoPessoa(TipoPessoa.FISICA);

		}

	}

	private void preparaPessoaJuridica(Pessoa pessoa, @Valid PessoaDTO dto) throws Exception {

		if (pessoa.getPessoaJuridica() != null) {

			if (dto.getJuridica().getCnpj() == null)
				throw new Exception("CNPJ obrigatório");

			Pessoa pesquisa = repository.findByCnpj(dto.getJuridica().getCnpj());
			if (pesquisa != null && !pesquisa.getId().equals(pessoa.getId()))
				throw new Exception("CNPJ já cadastrado para outra pessoa");

			PessoaJuridica juridica = pesquisa != null ? pesquisa.getPessoaJuridica() : new PessoaJuridica();
			if (juridica == null) {
				juridica = pessoaJuridicaMapper.toModel(dto.getJuridica());
				juridica.controle.setCadastro_dt(DateUtil.agora());
			} else {
				pessoaJuridicaMapper.updateModel(dto.getJuridica(), juridica);
				juridica.controle.setAtualizacao_dt(DateUtil.agora());
			}

			juridica = pessoaJuridicaRepository.saveAndFlush(juridica);
			pessoa.setPessoaJuridica(juridica);
			pessoa.setTipoPessoa(TipoPessoa.JURIDICA);

		}

	}

}
