package com.ibm.alan.ponto.seguranca;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class JWTObjeto {
	
	private String subject; // nome do usuário
	private Date issuedAt; // data de criação do token
	private Date expiration; // data de expiração do token
	private List<String> roles; //perfis de acesso

}
