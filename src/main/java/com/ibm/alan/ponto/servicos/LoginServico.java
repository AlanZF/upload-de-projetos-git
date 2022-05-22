package com.ibm.alan.ponto.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.alan.ponto.entidades.Usuario;
import com.ibm.alan.ponto.repositorios.UsuarioRepositorio;

@Service
public class LoginServico {

	@Autowired
	private UsuarioRepositorio loginRepositorio;

	@Autowired
	private PasswordEncoder encoder;

	public Usuario cadastrar(Usuario usuario) {

		String senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));

		return loginRepositorio.save(usuario);
	}

	public Optional<Usuario> findByLogin(String login) {
		return loginRepositorio.findByLogin(login);
	}

	public Optional<Usuario> findByTelefone(String telefone) {
		return loginRepositorio.findByTelefone(telefone);
	}

}
