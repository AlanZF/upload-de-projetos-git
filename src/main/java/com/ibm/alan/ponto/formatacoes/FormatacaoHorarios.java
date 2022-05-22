package com.ibm.alan.ponto.formatacoes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormatacaoHorarios {
	
	public int converteStrParaMilliSec(String horario) {
		
		LocalTime horarioTime = LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		int horaMiliSeg = horarioTime.getHour() * 3600000;
		int minutosMiliSeg = horarioTime.getMinute() * 60000;
		int segundosMiliSeg = horarioTime.getSecond() * 1000;
		
		int resultado = horaMiliSeg + minutosMiliSeg + segundosMiliSeg;
		return resultado;
	}
	
	public String converteMilliParaStr(int horarioMilliSeg) {
		
		int segundos = (horarioMilliSeg/1000) % 60;
		int minutos  = (horarioMilliSeg/60000) % 60;
		int horas = (horarioMilliSeg/3600000);
	    
		String resultado = String.format("%02d:%02d:%02d", horas, minutos, segundos);
		
		return resultado;
	}
	
}
