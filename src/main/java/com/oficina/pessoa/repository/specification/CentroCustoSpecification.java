package com.oficina.pessoa.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.oficina.financeiro.model.CentroCusto;
import com.oficina.pessoa.filter.CentroCustoFilter;

public class CentroCustoSpecification implements Specification<CentroCusto> {

	private static final long serialVersionUID = 1L;

	private CentroCustoFilter filtro;
	List<Predicate> predicates = new ArrayList<>();

	public CentroCustoSpecification(CentroCustoFilter filtro) {
		this.filtro = filtro;
	}

	@Override
	public Predicate toPredicate(Root<CentroCusto> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		if (filtro.getId() != null)
			predicates.add(builder.equal(root.get("id"), filtro.getId()));

		if (filtro.getDescricao() != null) {
			predicates.add(builder.like(root.get("descricao"), "%" + filtro.getDescricao() + "%"));
		}

		if (filtro.getTipo() != null) {
			predicates.add(builder.equal(root.get("tipo"), filtro.getTipo()));
		}

		if (filtro.getAtivo() != null) {
			predicates.add(builder.equal(root.get("ativo"), filtro.getAtivo()));
		}

		query.orderBy(builder.desc(root.get("tipo")), builder.asc(root.get("descricao")));

		return builder.and(predicates.toArray(new Predicate[0]));
	}

}
