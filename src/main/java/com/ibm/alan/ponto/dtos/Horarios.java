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
public class Horarios {

	@JsonFormat(pattern = "HH:mm:ss")
	private String horario;
	
	public Horarios(Ponto ponto) {
		this.horario = ponto.getHorario();
	}
	
	public static List<Horarios> converter(List<Ponto> ponto) {
        return ponto.stream().map(Horarios::new).collect(Collectors.toList());
    }

}
