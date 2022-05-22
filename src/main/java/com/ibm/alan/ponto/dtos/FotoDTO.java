package com.ibm.alan.ponto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FotoDTO {
	
	private Integer idFoto;
	private String tipo;
	private String nomeImagem;
	
}

