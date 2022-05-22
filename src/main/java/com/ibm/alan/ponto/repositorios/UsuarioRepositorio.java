package com.ibm.alan.ponto.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.alan.ponto.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByLogin(@Param ("login") String login);
	
	public Optional<Usuario> findByTelefone(@Param ("telefone") String telefone);
	
	public Integer countByIdUsuario(Integer idUsuario);
	
	public Usuario getOne(@Param ("idUsuario") Integer idUsuario);


}
