package com.oficina.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.oficina.model.filter.PessoaFilter;
import com.oficina.model.pessoa.Cliente;

public class ClienteSpecification implements Specification<Cliente> {

	private static final long serialVersionUID = 1L;

	private PessoaFilter filtro;
	List<Predicate> predicates = new ArrayList<>();

	public ClienteSpecification(PessoaFilter filtro) {
		this.filtro = filtro;
	}

	@Override
	public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		if (filtro.getId() != null)
			predicates.add(builder.equal(root.get("id"), filtro.getId()));

		if (filtro.getNome() != null) {
			predicates.add(builder.or(builder.like(root.get("pessoa").get("nome"), "%" + filtro.getNome() + "%"),
					builder.like(root.get("pessoa").get("email"), "%" + filtro.getNome() + "%")));
		}

		if (filtro.getAtivo() != null) {
			predicates.add(builder.equal(root.get("controle.ativo"), filtro.getAtivo()));
		}

		query.orderBy(builder.asc(root.get("pessoa").get("nome")));

		return builder.and(predicates.toArray(new Predicate[0]));
	}

}
