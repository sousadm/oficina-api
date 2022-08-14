package com.oficina.model.pessoa;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.oficina.model.enums.Ativo;

import lombok.Data;

@Data
@Embeddable
public class Controle {

	private LocalDateTime cadastro_dt;

	private LocalDateTime atualizacao_dt;

	@Enumerated(EnumType.ORDINAL)
	private Ativo ativo = Ativo.ATIVO;

}
