package com.ibm.alan.ponto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibm.alan.ponto.entidades.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTOResposta {
	           
	private String nome;
	
	private String telefone;
	      
	private String login;
	  
	private String senha;
	
	@JsonFormat(pattern = "dd-MM-YY HH:mm:ss")    
	private String created;
	
	@JsonFormat(pattern = "dd-MM-YY HH:mm:ss")    
	private String edited;
	
	public UsuarioDTOResposta transformaEntidadeParaDTO(Usuario usuario) {
		return new UsuarioDTOResposta(usuario.getNome(), usuario.getTelefone(), usuario.getLogin(),
				usuario.getSenha(), usuario.getCreated(), usuario.getEdited());
	}

	
}
