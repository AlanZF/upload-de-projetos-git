package com.ibm.alan.ponto.dtos;

import lombok.Data;

@Data
public class Sessao {
	
	private String login;
	private String token;
	private Integer idUsuario;
	
}
