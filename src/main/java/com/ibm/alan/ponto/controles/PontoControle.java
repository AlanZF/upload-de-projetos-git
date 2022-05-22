package com.ibm.alan.ponto.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.alan.ponto.dtos.Horarios;
import com.ibm.alan.ponto.dtos.HorariosData;
import com.ibm.alan.ponto.entidades.Ponto;
import com.ibm.alan.ponto.entidades.Usuario;
import com.ibm.alan.ponto.formatacoes.FormatacaoDatas;
import com.ibm.alan.ponto.formatacoes.FormatacaoHorarios;
import com.ibm.alan.ponto.servicos.PontoServico;
import com.ibm.alan.ponto.servicos.UsuarioServico;

@RestController
@RequestMapping(value = "/api/ponto")
public class PontoControle {

	@Autowired
	private PontoServico pontoServico;

	@Autowired
	private UsuarioServico usuarioServico;

	private FormatacaoDatas formatacaoDatas;

	private FormatacaoHorarios formatacaoHorarios;

	@RequestMapping(value = "/registrarPonto/{idUsuario}", method = RequestMethod.POST)
	public ResponseEntity<String> registrarPonto(@PathVariable Integer idUsuario) {

		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		Usuario usuario = optUsuario.get();

		Ponto ponto = new Ponto();
		ponto.setUsuario(usuario);

		formatacaoDatas = new FormatacaoDatas();
		ponto.setData(formatacaoDatas.getDataHojeFormatada());
		ponto.setDia(formatacaoDatas.getDiaFormatado());
		ponto.setHorario(formatacaoDatas.getHorarioHojeFormatado());
		ponto.setMes(formatacaoDatas.getMesFormatado());

		String data = formatacaoDatas.getDataHojeFormatada();

		Integer numMarcacoes = pontoServico.contarMarcacoesDia(usuario, data);
		
		// setando horas trabalhadas
		formatacaoDatas = new FormatacaoDatas();
		String mes = formatacaoDatas.getMesFormatado();
		String dia = formatacaoDatas.getDiaFormatado();

		List<Horarios> horarios = pontoServico.findByUsuarioAndMesAndDia(usuario, mes, dia);

		if (numMarcacoes == 0) {
			ponto.setHorasTrabalhadas("00:00:00");
			
		} else if (numMarcacoes >= 1) {
			
			String marc1 = horarios.get(0).toString().substring(17, 25);
			String marc2 = formatacaoDatas.getHorarioHojeFormatado();
			
			formatacaoHorarios = new FormatacaoHorarios();
			int marc1Milli = formatacaoHorarios.converteStrParaMilliSec(marc1);
			int marc2Milli = formatacaoHorarios.converteStrParaMilliSec(marc2);
			
			int hrsTrab = marc2Milli - marc1Milli;
			
			String hrsTrabStr = formatacaoHorarios.converteMilliParaStr(hrsTrab);
			ponto.setHorasTrabalhadas(hrsTrabStr);
		} 

		// verificando ponto pendente
		if (numMarcacoes >= 4) {
			return ResponseEntity.badRequest().body("Já foram registradas todas as marcações para data de hoje.");
		} else {
			pontoServico.registrarPonto(ponto);
			return ResponseEntity.ok().body("Ponto registrado com sucesso!");
		}
	}

	@RequestMapping(value = "/consultarPonto/{idUsuario}", method = RequestMethod.GET)
	public List<Ponto> consultarPonto(@PathVariable Integer idUsuario) {

		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		Usuario usuario = optUsuario.get();

		formatacaoDatas = new FormatacaoDatas();
		String data = formatacaoDatas.getDataHojeFormatada();

		List<Ponto> listaPonto = pontoServico.findByDataAndUsuario(data, usuario);
		return listaPonto;
	}

	@RequestMapping(value = "/verEspelho/{idUsuario}/{dia}", method = RequestMethod.GET)
	public List<Horarios> verEspelhoPorIdDia(@PathVariable Integer idUsuario, @PathVariable String dia) {

		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		Usuario usuario = optUsuario.get();

		formatacaoDatas = new FormatacaoDatas();
		String mes = formatacaoDatas.getMesFormatado();

		List<Horarios> horarios = pontoServico.findByUsuarioAndMesAndDia(usuario, mes, dia);

		return horarios;
	}

	@RequestMapping(value = "/verEspelhoComData/{idUsuario}", method = RequestMethod.GET)
	public List<HorariosData> verEspelhoPorId(@PathVariable Integer idUsuario) {

		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		Usuario usuario = optUsuario.get();

		formatacaoDatas = new FormatacaoDatas();
		String mes = formatacaoDatas.getMesFormatado();

		List<HorariosData> horariosData = pontoServico.findByUsuarioAndMes(usuario, mes);

		return horariosData;
	}

	@RequestMapping(value = "/statusPonto/{idUsuario}", method = RequestMethod.GET)
	public ResponseEntity<String> statusPonto(@PathVariable Integer idUsuario) {

		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		Usuario usuario = optUsuario.get();

		formatacaoDatas = new FormatacaoDatas();
		String data = formatacaoDatas.getDataHojeFormatada();

		Integer numMarcacoes = pontoServico.contarMarcacoesDia(usuario, data);

		if (numMarcacoes >= 4) {
			return ResponseEntity.ok().body("Sem marcação pendente");
		} else {
			return ResponseEntity.ok().body("Marcação pendente");
		}
	}

}
