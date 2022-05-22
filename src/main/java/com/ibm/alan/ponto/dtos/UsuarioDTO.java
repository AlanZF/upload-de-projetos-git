package com.ibm.alan.ponto.dtos;

import com.ibm.alan.ponto.entidades.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private String nome;
	private String telefone;
	private String login;
	private String senha;
	
	public Usuario transformaDTOParaEntidade() {
		return new Usuario(nome, telefone, login, senha);
	}

}
