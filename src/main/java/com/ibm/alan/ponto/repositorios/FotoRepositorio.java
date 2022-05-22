package com.ibm.alan.ponto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.alan.ponto.entidades.Foto;
import com.ibm.alan.ponto.entidades.Usuario;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, Integer>{

	public Foto findByUsuario(Usuario usuario);

	public List<Foto> findAllByUsuario(Usuario usuario);
	
}
