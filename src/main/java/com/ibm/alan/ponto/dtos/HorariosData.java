package com.ibm.alan.ponto.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibm.alan.ponto.entidades.Ponto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorariosData {
	
	@JsonFormat(pattern = "dd-MM-YY")
	private String data;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private String horario;
	
	private String horasTrabalhadas;
	
	public HorariosData(Ponto ponto) {
		this.data = ponto.getData();
		this.horario = ponto.getHorario();
		this.horasTrabalhadas = ponto.getHorasTrabalhadas();
	}
	
	public static List<HorariosData> converter(List<Ponto> ponto) {
        return ponto.stream().map(HorariosData::new).collect(Collectors.toList());
    }

}
