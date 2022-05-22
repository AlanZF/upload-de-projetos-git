package com.ibm.alan.ponto.controles;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.alan.ponto.servicos.LoginServico;
import com.ibm.alan.ponto.servicos.UsuarioServico;
import com.ibm.alan.ponto.dtos.Nome;
import com.ibm.alan.ponto.entidades.Usuario;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioControle {
	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@Autowired
	private LoginServico loginServico;
	
	@RequestMapping(value = "/retornaNome/{idUsuario}", method = RequestMethod.GET)
	public Nome retornaNome(@PathVariable Integer idUsuario) {
		
		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		
		if(!optUsuario.isPresent()) {
			throw new NoSuchElementException("Usuário não existe na base de dados.");
		}
		
		Usuario usuario = optUsuario.get();
		
		Nome nome = new Nome();
		nome.setNome(usuario.getNome());
		
		return nome;
	}
	
	@RequestMapping(value = "/validaLogin/{login}", method = RequestMethod.GET)
	public ResponseEntity<String> validaLogin(@PathVariable String login) {
		
		Optional<Usuario> optLogin = loginServico.findByLogin(login);
		if(optLogin.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário não disponível. Escolha outro.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Ok!");
		}
	}
	
	@RequestMapping(value = "/validaSenha/{senha}", method = RequestMethod.GET)
	public ResponseEntity<String> validaSenha(@PathVariable String senha) {
		
		boolean validaSenha = senha.length() >= 8;
		
		HttpStatus status = (validaSenha) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		String mensagem = (validaSenha) ? "Ok!" : "A senha precisa ter pelo menos 8 caracteres.";
		
		return ResponseEntity.status(status).body(mensagem);
	}
	
	@RequestMapping(value = "/validaTelefone/{telefone}", method = RequestMethod.GET)
	public ResponseEntity<String> validaTelefone(@PathVariable String telefone) {
		
		Optional<Usuario> optTelefone = loginServico.findByTelefone(telefone);
		
		boolean validaTelefone = telefone.matches("\\b\\d{2}[9]{1}\\d{8}?\\b");
		
		if(optTelefone.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Telefone já cadastrado.");
		} else if(!validaTelefone) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Telefone com formato inválido.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Telefone disponível.");
		}
	}
	
}