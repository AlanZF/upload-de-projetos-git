package com.ibm.alan.ponto.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.alan.ponto.repositorios.UsuarioRepositorio;
import com.ibm.alan.ponto.entidades.Usuario;

@Service
public class UsuarioServico {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public Optional<Usuario> findById(Integer idUsuario) {
		return usuarioRepositorio.findById(idUsuario);
	}
	
	public List<Usuario> findAll() {
		return usuarioRepositorio.findAll();
	}
	

}
