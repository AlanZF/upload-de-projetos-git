package com.ibm.alan.ponto.formatacoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatacaoDatas {
	
	private LocalDateTime dataHoje;
	private String dataHoraHojeFormatados;
	private String dataHojeFormatada;
	private String horarioHojeFormatado;
	private String mesFormatado;
	private String diaFormatado;

	public String getDiaFormatado() {
		DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd");
		diaFormatado = getDataHoje().format(formatoDia);
		return diaFormatado;
	}

	public LocalDateTime getDataHoje() {
		dataHoje = LocalDateTime.now();
		return dataHoje;
	}

	public String getDataHoraHojeFormatados() {
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd-MM-YY HH:mm:ss");
		dataHoraHojeFormatados = getDataHoje().format(formatoDataHora);
		return dataHoraHojeFormatados;
	}

	public String getDataHojeFormatada() {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-YY");
		dataHojeFormatada = getDataHoje().format(formatoData);
		return dataHojeFormatada;
	}

	public String getHorarioHojeFormatado() {
		DateTimeFormatter formatoHorario = DateTimeFormatter.ofPattern("HH:mm:ss");
		horarioHojeFormatado = getDataHoje().format(formatoHorario);
		return horarioHojeFormatado;
	}

	public String getMesFormatado() {
		DateTimeFormatter formatoMes = DateTimeFormatter.ofPattern("MM");
		mesFormatado = getDataHoje().format(formatoMes);
		return mesFormatado;
	}
	
	public void setDataHoraHojeFormatados(String dataHoraHojeFormatados) {
		this.dataHoraHojeFormatados = dataHoraHojeFormatados;
	}
	
	public void setDataHoje(LocalDateTime dataHoje) {
		this.dataHoje = dataHoje;
	}

	public void setDataHojeFormatada(String dataHojeFormatada) {
		this.dataHojeFormatada = dataHojeFormatada;
	}

	public void setHorarioHojeFormatado(String horarioHojeFormatado) {
		this.horarioHojeFormatado = horarioHojeFormatado;
	}

	public void setMesFormatado(String mesFormatado) {
		this.mesFormatado = mesFormatado;
	}
	
	public void setDiaFormatado(String diaFormatado) {
		this.diaFormatado = diaFormatado;
	}

}
