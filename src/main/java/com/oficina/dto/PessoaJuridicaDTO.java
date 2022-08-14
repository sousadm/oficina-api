package com.oficina.dto;

import lombok.Data;

@Data
public class PessoaJuridicaDTO {

	private String fantasia;
	private String cnpj;
	private String IE;
	private String IESub;
	private String IM;
	private String emailNfe;
	private Boolean isentoIe = false;
	private Boolean retensaoIss = false;

}
