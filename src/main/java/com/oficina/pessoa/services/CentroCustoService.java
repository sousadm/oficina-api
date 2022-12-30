package com.oficina.pessoa.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oficina.financeiro.dto.CentroCustoDTO;
import com.oficina.financeiro.model.CentroCusto;
import com.oficina.infra.exception.RecursoNaoLocalizadoException;
import com.oficina.pessoa.filter.CentroCustoFilter;
import com.oficina.pessoa.mapper.CentroCustoMapper;
import com.oficina.pessoa.repository.CentroCustoRepository;
import com.oficina.pessoa.repository.specification.CentroCustoSpecification;

@Service
public class CentroCustoService {

	@Autowired
	private CentroCustoMapper mapper;

	@Autowired
	private CentroCustoRepository repository;

	public CentroCusto porId(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RecursoNaoLocalizadoException("Registro não localizada!"));
	}

	public List<CentroCustoDTO> lista(CentroCustoFilter filtro) {
		Specification<CentroCusto> spec = new CentroCustoSpecification(filtro);
		return repository.findAll(spec).stream().map(x -> this.mapper.toDto(x)).collect(Collectors.toList());
	}

	@Transactional
	public CentroCustoDTO salvar(@Valid CentroCustoDTO dto, Integer codigo) throws Exception {

		CentroCusto custoCusto = mapper.toModel(dto);

		if (codigo != null) {
			custoCusto = repository.findById(codigo)
					.orElseThrow(() -> new RecursoNaoLocalizadoException("registro não localizada!"));
			mapper.updateModel(dto, custoCusto);
		}

		return mapper.toDto(repository.save(custoCusto));

	}

	@Transactional
	public void deletar(CentroCusto model) {
		repository.delete(model);
	}

}
