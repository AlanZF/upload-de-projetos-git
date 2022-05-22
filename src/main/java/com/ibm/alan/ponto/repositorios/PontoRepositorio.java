package com.ibm.alan.ponto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.alan.ponto.entidades.Ponto;
import com.ibm.alan.ponto.entidades.Usuario;

@Repository
public interface PontoRepositorio extends JpaRepository< Ponto, Integer > {
	
	public List<Ponto> findByDataAndUsuario(String data, Usuario usuario);
	
	public Integer countByUsuarioAndData(Usuario usuario, String data);
	
	public List<Ponto> findByUsuarioAndMes(Usuario usuario, String mes);
	
	public List<Ponto> findByUsuarioAndMesAndDia(Usuario usuario, String mes, String dia);

}
