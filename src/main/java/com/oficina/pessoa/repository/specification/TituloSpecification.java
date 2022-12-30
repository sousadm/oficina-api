package com.oficina.pessoa.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.oficina.financeiro.filter.TituloFilter;
import com.oficina.financeiro.model.Titulo;

public class TituloSpecification implements Specification<Titulo> {

	private static final long serialVersionUID = 1L;

	private TituloFilter filtro;
	List<Predicate> predicates = new ArrayList<>();

	public TituloSpecification(TituloFilter filtro) {
		this.filtro = filtro;
	}

	@Override
	public Predicate toPredicate(Root<Titulo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		if (filtro.getId() != null)
			predicates.add(builder.equal(root.get("id"), filtro.getId()));

		if (filtro.getTipo() != null)
			predicates.add(builder.equal(root.get("tipo"), filtro.getTipo()));

		if (filtro.getVencimentoFinal() != null) {
			predicates.add(builder.between(root.get("vencimento_dt"), filtro.getVencimentoInicial(),
					filtro.getVencimentoFinal()));
		}

		if (filtro.getPrevisaoFinal() != null) {
			predicates.add(
					builder.between(root.get("previsao_dt"), filtro.getPrevisaoInicial(), filtro.getPrevisaoFinal()));
		}

		if (filtro.getDescricao() != null) {
			predicates.add(builder.like(root.get("descricao"), "%" + filtro.getDescricao() + "%"));
		}

		if (filtro.isEncerrado()) {
			predicates.add(builder.isNotEmpty(root.get("quitacao_dt")));
		}

		query.orderBy(builder.asc(root.get("responsavel").get("nome")));

		return builder.and(predicates.toArray(new Predicate[0]));
	}

}
