package com.oficina.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oficina.dto.TituloDTO;
import com.oficina.exception.RecursoNaoLocalizadoException;
import com.oficina.mapper.TituloMapper;
import com.oficina.model.enums.TipoRepeticao;
import com.oficina.model.filter.TituloFilter;
import com.oficina.model.financeiro.CentroCusto;
import com.oficina.model.financeiro.Titulo;
import com.oficina.model.pessoa.Pessoa;
import com.oficina.repository.CentroCustoRepository;
import com.oficina.repository.PessoaRepository;
import com.oficina.repository.TituloRepository;
import com.oficina.repository.specification.TituloSpecification;
import com.oficina.util.DateUtil;

@Service
public class TituloService {

	@Autowired
	private TituloMapper mapper;

	@Autowired
	private TituloRepository repository;

	@Autowired
	private CentroCustoRepository centroCustoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Titulo porId(Integer codigo) {
		Titulo model = repository.findById(codigo)
				.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));
		return model;
	}

	@Transactional
	public TituloDTO salvar(@Valid TituloDTO dto, Integer codigo) throws Exception {

		Pessoa responsavel = pessoaRepository.findById(dto.getIdResponsavel())
				.orElseThrow(() -> new RecursoNaoLocalizadoException("responsável não localizada!"));

		CentroCusto custo = centroCustoRepository.findById(dto.getIdCentroCusto())
				.orElseThrow(() -> new RecursoNaoLocalizadoException("centro de custo não localizada!"));

		Titulo titulo = mapper.toModel(dto);

		if (codigo != null) {
			titulo = repository.findById(codigo)
					.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));

			if (titulo.getQuitacao_dt() != null)
				throw new Exception("título já liquidado");

			mapper.updateModel(dto, titulo);
			titulo.controle.setAtualizacao_dt(DateUtil.agora());

		} else {
			titulo.setSaldo(titulo.getValor());
			titulo.controle.setCadastro_dt(DateUtil.agora());
		}

		if (titulo.getPrevisao_dt() == null || titulo.getPrevisao_dt().compareTo(titulo.getVencimento_dt()) < 0)
			titulo.setPrevisao_dt(titulo.getVencimento_dt());

		if (titulo.getRepeticao() == null)
			titulo.setRepeticao(TipoRepeticao.DESABILITADO);

		titulo.setCentroCusto(custo);
		titulo.setResponsavel(responsavel);

		return mapper.toDto(repository.save(titulo));

	}

	public Page<TituloDTO> listar(Pageable paginacao, TituloFilter filtro) {
		Specification<Titulo> spec = new TituloSpecification(filtro);
		return repository.findAll(spec, paginacao).map(x -> this.mapper.toDto(x));
	}

	@Transactional
	public void deletar(Titulo model) {
		repository.delete(model);
	}

}
