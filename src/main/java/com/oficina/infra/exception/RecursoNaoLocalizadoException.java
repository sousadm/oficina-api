package com.oficina.infra.exception;

public class RecursoNaoLocalizadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RecursoNaoLocalizadoException(String mensagem) {
		super(mensagem);
	}

	public RecursoNaoLocalizadoException() {
		super();
	}

}