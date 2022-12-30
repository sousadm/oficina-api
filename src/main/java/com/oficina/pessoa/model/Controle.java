package com.oficina.pessoa.model;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.oficina.pessoa.enums.Ativo;

import lombok.Data;

@Data
@Embeddable
public class Controle {

	private LocalDateTime cadastro_dt;

	private LocalDateTime atualizacao_dt;

	@Enumerated(EnumType.ORDINAL)
	private Ativo ativo = Ativo.ATIVO;

}
