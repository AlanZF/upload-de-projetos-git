package com.ibm.alan.ponto.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.alan.ponto.dtos.Horarios;
import com.ibm.alan.ponto.dtos.HorariosData;
import com.ibm.alan.ponto.entidades.Ponto;
import com.ibm.alan.ponto.entidades.Usuario;
import com.ibm.alan.ponto.repositorios.PontoRepositorio;

@Service
public class PontoServico {
	
	@Autowired
	private PontoRepositorio pontoRepositorio;
	
	public String registrarPonto(Ponto ponto) {
		
		pontoRepositorio.save(ponto);
		return "";
	}
	
	public List<Ponto> findByDataAndUsuario(String data, Usuario usuario) {
		return pontoRepositorio.findByDataAndUsuario(data, usuario);
	}
	
	public Integer contarMarcacoesDia(Usuario usuario, String data) {
		return pontoRepositorio.countByUsuarioAndData(usuario, data);
	}
	
	public List<Horarios> findByUsuarioAndMesAndDia(Usuario usuario, String mes, String dia){
        List<Ponto> pontos = pontoRepositorio.findByUsuarioAndMesAndDia(usuario, mes, dia);
        return Horarios.converter(pontos);
	}
	
	public List<HorariosData> findByUsuarioAndMes(Usuario usuario, String mes){
        List<Ponto> pontos = pontoRepositorio.findByUsuarioAndMes(usuario, mes);
        return HorariosData.converter(pontos);
	}

}
